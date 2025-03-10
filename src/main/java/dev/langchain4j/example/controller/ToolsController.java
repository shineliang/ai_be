package dev.langchain4j.example.controller;

import dev.langchain4j.example.model.Attendance;
import dev.langchain4j.example.model.Leave;
import dev.langchain4j.example.model.Shift;
import dev.langchain4j.example.service.tools.HRTools;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@RequiredArgsConstructor
public class ToolsController {

    private final HRTools hrTools;

    @GetMapping("/attendance")
    public List<Attendance> queryAttendance(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {
        return hrTools.queryAttendance(startDate, endDate != null ? endDate : startDate);
    }

    @GetMapping("/shift")
    public List<Shift> queryShift(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {
        return hrTools.queryShift(startDate, endDate != null ? endDate : startDate);
    }

    @PostMapping("/leave")
    public Leave applyLeave(@RequestBody Leave leave) {
        return hrTools.applyLeave(
            leave.getDate(),
            leave.getType(),
            leave.getDuration(),
            leave.getReason()
        );
    }

    @GetMapping("/leave/query")
    public List<Leave> queryLeave(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {
        return hrTools.queryLeave(startDate, endDate != null ? endDate : startDate);
    }
} 