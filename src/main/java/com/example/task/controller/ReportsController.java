package com.example.task.controller;

import com.example.task.dto.ReportsDto;
import com.example.task.dto.request.ReportsRequest;
import com.example.task.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reports")
public class ReportsController {

    final ReportsService reportsService;

    @GetMapping(value = "/reportList")
    public List<ReportsDto> getReportsInfo() {
        return reportsService.getAll();
    }

    @GetMapping(value = "/getReportsByReportID/{reportID}")
    public ReportsDto getReportsByReportID(@PathVariable(value = "reportID") Long reportID) {
        return reportsService.getReportsByReportID(reportID);
    }

    @PostMapping(value = "/saveReports")
    public void saveReports(@RequestBody ReportsRequest reportsRequest) {
        reportsService.saveReports(reportsRequest);
    }

    @PostMapping(value = "/updateReports")
    public void updateReports(@RequestBody ReportsRequest reportsRequest) {
        reportsService.updateReports(reportsRequest);
    }

    @DeleteMapping(value = "/deleteByReportsID/{reportsID}")
    public void deleteByReportsID(@PathVariable(value = "reportsID") Long reportsID) {
        reportsService.deleteByReportsID(reportsID);
    }

}
