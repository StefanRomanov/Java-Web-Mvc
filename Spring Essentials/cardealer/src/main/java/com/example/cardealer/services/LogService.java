package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.LogCreateModel;

import java.util.List;

public interface LogService {
    void addLog(LogCreateModel model);
    List<LogCreateModel> allLogs();
    List<LogCreateModel> allLogsByUsername(String username);
    void deleteAll();
}
