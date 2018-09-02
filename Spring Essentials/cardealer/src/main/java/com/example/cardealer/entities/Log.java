package com.example.cardealer.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
    private Long id;
    private String username;
    private String operation;
    private String modifiedTable;
    private LocalDateTime time;

    public Log() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getModifiedTable() {
        return modifiedTable;
    }

    public void setModifiedTable(String modifiedTable) {
        this.modifiedTable = modifiedTable;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
