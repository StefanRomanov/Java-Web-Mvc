package com.example.cardealer.entities.models.dto;


import java.time.LocalDateTime;

public class LogCreateModel {
    private String username;
    private String operation;
    private String modifiedTable;
    private LocalDateTime time;

    public LogCreateModel() {
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
