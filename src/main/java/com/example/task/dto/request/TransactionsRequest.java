package com.example.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsRequest {
    private Long transID;
    private Long employeeID;
    private Long customerID;
    private String name;
}
