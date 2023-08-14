package com.example.task.service.impl;

import com.example.task.dto.LogsDto;
import com.example.task.dto.request.LogsRequest;
import com.example.task.model.Logs;
import com.example.task.service.repository.LogsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LogsServiceImplTest {

    @Mock
    private LogsRepository logsRepository;

    @InjectMocks
    private LogsServiceImpl logsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        logsService = new LogsServiceImpl(logsRepository);
    }

    @Test
    public void testGetLogsByLogsID_ValidID_ShouldReturnDto() {
        // Arrange
        Long logsID = 1L;
        Logs logs = new Logs();
        logs.setLoginDate(String.valueOf(LocalDate.of(2023, 8, 9)));
        logs.setLoginTime(String.valueOf(LocalTime.of(12, 30)));

        when(logsRepository.getLogsByLogsID(logsID)).thenReturn(logs);

        // Act
        LogsDto logsDto = logsService.getLogsByLogsID(logsID);

        // Assert
        assertNotNull(logsDto);
        assertEquals(String.valueOf(LocalDate.of(2023, 8, 9)), logsDto.getLoginDate());
        assertEquals(String.valueOf(LocalTime.of(12, 30)), logsDto.getLoginTime());
    }

    @Test
    public void testSaveLogs_ValidRequest_ShouldSave() {
        // Arrange
        LogsRequest logsRequest = new LogsRequest();
        logsRequest.setLoginDate(String.valueOf(LocalDate.of(2023, 8, 9)));
        logsRequest.setLoginTime(String.valueOf(LocalTime.of(12, 30)));

        // Act
        logsService.saveLogs(logsRequest);

        // Assert
        verify(logsRepository, times(1)).save(any(Logs.class));
    }

    @Test
    public void testUpdateLogs_ValidRequest_ShouldUpdate() {
        // Arrange
        LogsRequest logsRequest = new LogsRequest();
        logsRequest.setLogsID(1L);
        logsRequest.setTransID(Long.valueOf("12345"));
        logsRequest.setLoginDate(String.valueOf(LocalDate.of(2023, 8, 9)));
        logsRequest.setLoginTime(String.valueOf(LocalTime.of(12, 30)));

        // Act
        logsService.updateLogs(logsRequest);

        // Assert
        verify(logsRepository, times(1)).save(any(Logs.class));
    }

    @Test
    public void testGetAll_ShouldReturnListOfDtos() {
        // Arrange
        List<Logs> logsList = new ArrayList<>();
        Logs logs1 = new Logs();
        logs1.setLoginDate(String.valueOf(LocalDate.of(2023, 8, 9)));
        logs1.setLoginTime(String.valueOf(LocalTime.of(12, 30)));
        Logs logs2 = new Logs();
        logs2.setLoginDate(String.valueOf(LocalDate.of(2023, 8, 10)));
        logs2.setLoginTime(String.valueOf(LocalTime.of(13, 0)));
        logsList.add(logs1);
        logsList.add(logs2);

        when(logsRepository.findAll()).thenReturn(logsList);

        // Act
        List<LogsDto> logsDtos = logsService.getAll();

        // Assert
        assertNotNull(logsDtos);
        assertEquals(2, logsDtos.size());
        assertEquals(String.valueOf(LocalDate.of(2023, 8, 9)), logsDtos.get(0).getLoginDate());
        assertEquals(String.valueOf(LocalTime.of(12, 30)), logsDtos.get(0).getLoginTime());
        assertEquals(String.valueOf(LocalDate.of(2023, 8, 10)), logsDtos.get(1).getLoginDate());
        assertEquals(String.valueOf(LocalTime.of(13, 0)), logsDtos.get(1).getLoginTime());
    }

    @Test
    public void testDeleteByLogsID_ExistingID_ShouldDelete() {
        // Arrange
        Long logsID = 1L;
        Logs logs = new Logs();
        logs.setLogsID(logsID);

        when(logsRepository.getLogsByLogsID(logsID)).thenReturn(logs);

        // Act
        logsService.deleteByLogsID(logsID);

        // Assert
        verify(logsRepository, times(1)).deleteById(logsID);
    }

    @Test
    public void testDeleteByLogsID_NonExistingID_ShouldThrowException() {
        // Arrange
        Long logsID = 1L;
        when(logsRepository.getLogsByLogsID(logsID)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> logsService.deleteByLogsID(logsID));
        verify(logsRepository, never()).deleteById(logsID);
    }
}
