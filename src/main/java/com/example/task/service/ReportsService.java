package com.example.task.service;

import com.example.task.dto.ReportsDto;
import com.example.task.dto.request.ReportsRequest;

import java.util.List;

public interface ReportsService {

    ReportsDto getReportsByReportID(Long reportID);

    void saveReports(ReportsRequest repotrsRequest);

    void updateReports(ReportsRequest reportsRequest);

    List<ReportsDto> getAll();

    void deleteByReportsID(Long reportsID);
}
