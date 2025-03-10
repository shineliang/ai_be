package dev.langchain4j.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String detail;
} 