package com.task.management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    @JsonProperty("Task")
    private String Task;
    @JsonProperty("Priority")
    private Integer Priority;
    @JsonProperty("Deadline")
    private String Deadline;
}
