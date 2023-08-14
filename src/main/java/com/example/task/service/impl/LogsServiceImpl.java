package com.example.task.service.impl;

import com.example.task.dto.LogsDto;
import com.example.task.dto.request.LogsRequest;
import com.example.task.model.Logs;
import com.example.task.service.repository.LogsRepository;
import com.example.task.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogsServiceImpl implements LogsService {

    private final LogsRepository logsRepository;

    @Override
    public LogsDto getLogsByLogsID(Long logsID) {
        Logs logs = logsRepository.getLogsByLogsID(logsID);
        return LogsDto.builder()
                .loginDate(logs.getLoginDate())
                .loginTime(logs.getLoginTime())
                .build();
    }

    @Override
    public void saveLogs(LogsRequest logsRequest) {
        Logs logs = Logs.builder()
                .loginDate(logsRequest.getLoginDate())
                .loginTime(logsRequest.getLoginTime())
                .build();

        logsRepository.save(logs);
    }

    @Override
    public void updateLogs(LogsRequest logsRequest) {
        Logs logs = Logs.builder()
                .logsID(logsRequest.getLogsID())
                .transID(logsRequest.getTransID())
                .loginDate(logsRequest.getLoginDate())
                .loginTime(logsRequest.getLoginTime())
                .build();

        logsRepository.save(logs);
    }

    @Override
    public List<LogsDto> getAll() {
        List<Logs> logs = logsRepository.findAll();
        List<LogsDto> logsDtos = new ArrayList<>();

        for (int i = 0; i < logs.size(); i++) {
            LogsDto logsDto = LogsDto.builder()
                    .loginDate(logs.get(i).getLoginDate())
                    .loginTime(logs.get(i).getLoginTime())
                    .build();

            logsDtos.add(logsDto);
        }

        return logsDtos;
    }

    @Override
    public void deleteByLogsID(Long logsID) {

        Logs logs = logsRepository.getLogsByLogsID(logsID);
        if (logs.getLogsID().equals(null)) {
            throw new RuntimeException("There is no such information");
        } else {
            logsRepository.deleteById(logsID);
        }

    }
}
