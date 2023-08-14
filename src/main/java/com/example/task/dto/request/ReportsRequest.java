package com.example.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportsRequest {
    private Long reportID;
    private Long accID;
    private Long logsID;
    private Long transID;
    private String reportName;
    private String reportDate;
}