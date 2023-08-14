package com.example.task.service;

import com.example.task.dto.LogsDto;
import com.example.task.dto.request.LogsRequest;

import java.util.List;

public interface LogsService {

    LogsDto getLogsByLogsID(Long logsID);

    void saveLogs(LogsRequest logsRequest);

    void updateLogs(LogsRequest logsRequest);

    List<LogsDto> getAll();

    void deleteByLogsID(Long logsID);

}
