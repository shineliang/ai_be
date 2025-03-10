package dev.langchain4j.example.aiservice;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
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
    @Tool
    String currentTime() {
        return LocalTime.now().toString();
    }

    private final AttendanceRepository attendanceRepository;
    private final ShiftRepository shiftRepository;
    private final LeaveRepository leaveRepository;

    @Tool
    public List<Attendance> queryAttendance(String startDate, String endDate) {
        return attendanceRepository.findByDateBetween(startDate, endDate);
    }

    @Tool
    public List<Shift> queryShift(String startDate, String endDate) {
        return shiftRepository.findByDateBetween(startDate, endDate);
    }

    @Tool
    public Leave applyLeave(String date, String type, String duration, String reason) {
        Leave leave = new Leave();
        leave.setDate(date);
        leave.setType(type);
        leave.setDuration(duration);
        leave.setReason(reason);
        return leaveRepository.save(leave);
    }

    @Tool
    public List<Leave> queryLeave(String startDate, String endDate) {
        return leaveRepository.findByDateBetween(startDate, endDate);
    }
}
