package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportID;
    @Column(name = "acc_id")
    private Long accID;
    @Column(name = "logs_id")
    private Long logsID;
    @Column(name = "trans_id")
    private Long transID;
    @Column(name = "report_name")
    private String reportName;
    @Column(name = "report_date")
    private String reportDate;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounts_acc_id")
    private Accounts accounts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logs_logs_id")
    private Logs logs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactions_trans_id")
    private Transactions transactions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reports_report_id")
    private Reports reports;

    @OneToMany(mappedBy = "reports", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reports> reportses = new ArrayList<>();



}
