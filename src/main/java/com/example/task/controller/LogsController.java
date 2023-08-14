package com.example.task.controller;

import com.example.task.dto.LogsDto;
import com.example.task.dto.request.LogsRequest;
import com.example.task.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/logs")
public class LogsController {

    final LogsService logsService;

    @GetMapping(value = "/logsList")
    public List<LogsDto> getLogsInfo() {
        return logsService.getAll();
    }

    @GetMapping(value = "/getLogsByLogsID/{logsID}")
    public LogsDto getLogsByLogsID(@PathVariable(value = "logsID") Long logsID) {
        return logsService.getLogsByLogsID(logsID);
    }

    @PostMapping(value = "/saveLogs")
    public void saveLogs(@RequestBody LogsRequest logsRequest){
        logsService.saveLogs(logsRequest);
    }

    @PostMapping(value = "/updateLogs")
    public void updateLogs(@RequestBody LogsRequest logsRequest){
        logsService.updateLogs(logsRequest);
    }

    @DeleteMapping(value = "/deleteByLogsID/{logsID}")
    public void deleteByLogsID(@PathVariable(value = "logsID") Long logsID) {
        logsService.deleteByLogsID(logsID);
    }

}
