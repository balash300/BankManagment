package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logs_id")
    private Long logsID;
    @Column(name = "trans_id")
    private Long transID;
    @Column(name = "login_date")
    private String loginDate;
    @Column(name = "login_time")
    private String loginTime;



    @OneToMany(mappedBy = "logs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reports> reports = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactions_trans_id")
    private Transactions transactions;



}
