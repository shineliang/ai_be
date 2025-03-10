package dev.langchain4j.example.repository;

import dev.langchain4j.example.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByDateBetween(String startDate, String endDate);
} 