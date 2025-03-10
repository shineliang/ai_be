package dev.langchain4j.example.repository;

import dev.langchain4j.example.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDateBetween(String startDate, String endDate);
} 