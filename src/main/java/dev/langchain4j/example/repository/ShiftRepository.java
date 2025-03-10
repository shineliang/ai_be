package dev.langchain4j.example.repository;

import dev.langchain4j.example.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByDateBetween(String startDate, String endDate);
} 