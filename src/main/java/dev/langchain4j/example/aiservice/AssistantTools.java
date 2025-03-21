package dev.langchain4j.example.aiservice;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.langchain4j.example.model.Attendance;
import dev.langchain4j.example.model.Leave;
import dev.langchain4j.example.model.Shift;
import dev.langchain4j.example.repository.AttendanceRepository;
import dev.langchain4j.example.repository.LeaveRepository;
import dev.langchain4j.example.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
@Component
@RequiredArgsConstructor
class AssistantTools {

    /**
     * This tool is available to {@link Assistant}
     */
    @Tool(name = "当前时间")
    String currentTime() {
        return LocalTime.now().toString();
    }

    private final AttendanceRepository attendanceRepository;
    private final ShiftRepository shiftRepository;
    private final LeaveRepository leaveRepository;

    @Tool(name = "查询考勤")
    public List<Attendance> queryAttendance(String startDate, String endDate) {
        return attendanceRepository.findByDateBetween(startDate, endDate);
    }

    // @Tool
    // public String queryShift(String startDate, String endDate) {
    //     // return shiftRepository.findByDateBetween(startDate, endDate);
    //     return "<<JSON_START>>" + "{\"shifts\":[{\"date\":\"2025-03-15\",\"shift\":\"Day\"},{\"date\":\"2025-03-16\",\"shift\":\"Night\"}]}" + "<<JSON_END>>";
    // }
    @Tool(name = "查询排班")
    public String queryShift(String startDate, String endDate) throws JsonProcessingException {
        List<Shift> shifts = shiftRepository.findByDateBetween(startDate, endDate);
        ObjectMapper objectMapper = new ObjectMapper();
        String shiftsJson = objectMapper.writeValueAsString(shifts);
        // return "```formapply\n" + shiftsJson + "\n```";
        String formapply = "```formapply\n{\n" +
        "  \"baseUrl\": \"https://api.example.com\",\n" +
        "  \"path\": \"/apply/\",\n" +
        "  \"params\": {\n" +
        "    \"id\": \"12345\",\n" +
        "    \"type\": \"form\",\n" +
        "    \"template\": \"standard\",\n" +
        "    \"source\": \"marketing\"\n" +
        "  }\n" +
        "}" +
        "\n```";
        return formapply;
        // String mermaidCode = "```mermaid\n" +
        // "graph TD\n" +
        // "  A[开始] --> B{判断条件}\n" +
        // "  B -->|是| C[处理1]\n" +
        // "  B -->|否| D[处理2]\n" +
        // "  C --> E[结束]\n" +
        // "  D --> E\n" +
        // "```";
        // return mermaidCode;
    }

    @Tool(name = "提交请假")
    public Leave applyLeave(String date, String type, String duration, String reason) {
        Leave leave = new Leave();
        leave.setDate(date);
        leave.setType(type);
        leave.setDuration(duration);
        leave.setReason(reason);
        return leaveRepository.save(leave);
    }

    @Tool(name = "查询请假")
    public List<Leave> queryLeave(String startDate, String endDate) {
        return leaveRepository.findByDateBetween(startDate, endDate);
    }
}
