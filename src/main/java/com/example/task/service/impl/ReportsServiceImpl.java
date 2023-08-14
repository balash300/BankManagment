package com.example.task.service.impl;

import com.example.task.dto.ReportsDto;
import com.example.task.dto.request.ReportsRequest;
import com.example.task.model.Reports;
import com.example.task.service.repository.ReportsRepository;
import com.example.task.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsServiceImpl implements ReportsService {

    private final ReportsRepository reportsRepository;

    @Override
    public ReportsDto getReportsByReportID(Long reportID) {
        Reports reports = reportsRepository.getReportsByReportID(reportID);
        return ReportsDto.builder()
                .reportName(reports.getReportName())
                .reportDate(reports.getReportDate())
                .build();
    }

    @Override
    public void saveReports(ReportsRequest reportsRequest) {
        Reports reports = Reports.builder()
                .reportName(reportsRequest.getReportName())
                .reportDate(reportsRequest.getReportDate())
                .build();

        reportsRepository.save(reports);
    }

    @Override
    public void updateReports(ReportsRequest reportsRequest) {
        Reports reports = Reports.builder()
                .reportID(reportsRequest.getReportID())
                .accID(reportsRequest.getAccID())
                .logsID(reportsRequest.getLogsID())
                .transID(reportsRequest.getTransID())
                .reportName(reportsRequest.getReportName())
                .reportDate(reportsRequest.getReportDate())
                .build();

        reportsRepository.save(reports);
    }

    @Override
    public List<ReportsDto> getAll() {
        List<Reports> reports = reportsRepository.findAll();
        List<ReportsDto> reportsDtos = new ArrayList<>();

        for (Reports report : reports) {
            ReportsDto reportsDto = ReportsDto.builder()
                    .reportName(report.getReportName())
                    .reportDate(report.getReportDate())
                    .build();

            reportsDtos.add(reportsDto);
        }

        return reportsDtos;
    }

    @Override
    public void deleteByReportsID(Long reportsID) {

        Reports reports = reportsRepository.getReportsByReportID(reportsID);
        if (reports.getReportID().equals(null)) {
            throw new RuntimeException("There is no such information");
        } else {
            reportsRepository.deleteById(reportsID);
        }

    }
}
