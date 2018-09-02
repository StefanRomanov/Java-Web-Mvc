package com.example.cardealer.services;

import com.example.cardealer.entities.Log;
import com.example.cardealer.entities.models.dto.LogCreateModel;
import com.example.cardealer.repos.LogRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addLog(LogCreateModel model) {
        Log log = this.modelMapper.map(model,Log.class);
        this.logRepository.saveAndFlush(log);
    }

    @Override
    public List<LogCreateModel> allLogs() {
        List<Log> logs = this.logRepository.findAll();
        Type type = new TypeToken<List<LogCreateModel>>(){}.getType();

        return this.modelMapper.map(logs,type);
    }

    @Override
    public List<LogCreateModel> allLogsByUsername(String username) {
        List<Log> logs = this.logRepository.findAllByUsername(username);
        Type type = new TypeToken<List<LogCreateModel>>(){}.getType();

        return this.modelMapper.map(logs,type);
    }

    @Override
    public void deleteAll() {
        this.logRepository.deleteAll();
    }
}
