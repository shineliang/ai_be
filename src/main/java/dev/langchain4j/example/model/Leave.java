package dev.langchain4j.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "leave")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String type;
    private String duration;
    private String reason;
} 