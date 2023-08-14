package com.example.task.service.impl;

import com.example.task.dto.ReportsDto;
import com.example.task.dto.request.ReportsRequest;
import com.example.task.model.Reports;
import com.example.task.service.repository.ReportsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportsServiceImplTest {

    @Mock
    private ReportsRepository reportsRepository;

    @InjectMocks
    private ReportsServiceImpl reportsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        reportsService = new ReportsServiceImpl(reportsRepository);
    }

    @Test
    public void testGetReportsByReportID_ValidID_ShouldReturnDto() {
        // Arrange
        Long reportID = 1L;
        Reports reports = new Reports();
        reports.setReportName("Monthly Report");
        reports.setReportDate(String.valueOf(LocalDate.of(2023, 8, 9)));

        when(reportsRepository.getReportsByReportID(reportID)).thenReturn(reports);

        // Act
        ReportsDto reportsDto = reportsService.getReportsByReportID(reportID);

        // Assert
        assertNotNull(reportsDto);
        assertEquals("Monthly Report", reportsDto.getReportName());
        assertEquals(String.valueOf(LocalDate.of(2023, 8, 9)), reportsDto.getReportDate());
    }

    @Test
    public void testSaveReports_ValidRequest_ShouldSave() {
        // Arrange
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setReportName("Annual Report");
        reportsRequest.setReportDate(String.valueOf(LocalDate.of(2023, 12, 31)));

        // Act
        reportsService.saveReports(reportsRequest);

        // Assert
        verify(reportsRepository, times(1)).save(any(Reports.class));
    }

    @Test
    public void testUpdateReports_ValidRequest_ShouldUpdate() {
        // Arrange
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setReportID(1L);
        reportsRequest.setAccID(Long.valueOf("123"));
        reportsRequest.setLogsID(2L);
        reportsRequest.setTransID(Long.valueOf("456"));
        reportsRequest.setReportName("Updated Report");
        reportsRequest.setReportDate(String.valueOf(LocalDate.of(2023, 8, 9)));

        // Act
        reportsService.updateReports(reportsRequest);

        // Assert
        verify(reportsRepository, times(1)).save(any(Reports.class));
    }

    @Test
    public void testGetAll_ShouldReturnListOfDtos() {
        // Arrange
        List<Reports> reportsList = new ArrayList<>();
        Reports report1 = new Reports();
        report1.setReportName("Monthly Report");
        report1.setReportDate(String.valueOf(LocalDate.of(2023, 8, 9)));
        Reports report2 = new Reports();
        report2.setReportName("Yearly Report");
        report2.setReportDate(String.valueOf(LocalDate.of(2023, 12, 31)));
        reportsList.add(report1);
        reportsList.add(report2);

        when(reportsRepository.findAll()).thenReturn(reportsList);

        // Act
        List<ReportsDto> reportsDtos = reportsService.getAll();

        // Assert
        assertNotNull(reportsDtos);
        assertEquals(2, reportsDtos.size());
        assertEquals("Monthly Report", reportsDtos.get(0).getReportName());
        assertEquals(String.valueOf(LocalDate.of(2023, 8, 9)), reportsDtos.get(0).getReportDate());
        assertEquals("Yearly Report", reportsDtos.get(1).getReportName());
        assertEquals(String.valueOf(LocalDate.of(2023, 12, 31)), reportsDtos.get(1).getReportDate());
    }

    @Test
    public void testDeleteByReportsID_ExistingID_ShouldDelete() {
        // Arrange
        Long reportsID = 1L;
        Reports reports = new Reports();
        reports.setReportID(reportsID);

        when(reportsRepository.getReportsByReportID(reportsID)).thenReturn(reports);

        // Act
        reportsService.deleteByReportsID(reportsID);

        // Assert
        verify(reportsRepository, times(1)).deleteById(reportsID);
    }

    @Test
    public void testDeleteByReportsID_NonExistingID_ShouldThrowException() {
        // Arrange
        Long reportsID = 1L;
        when(reportsRepository.getReportsByReportID(reportsID)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reportsService.deleteByReportsID(reportsID));
        verify(reportsRepository, never()).deleteById(reportsID);
    }
}
