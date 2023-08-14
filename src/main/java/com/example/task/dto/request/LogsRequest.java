package com.example.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogsRequest {
    private Long logsID;
    private Long transID;
    private String loginDate;
    private String loginTime;
}
