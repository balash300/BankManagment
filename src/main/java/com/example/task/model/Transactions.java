package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long transID;
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "customer_id")
    private Long customerID;
    private String name;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employees_employee_id")
    private Employees employees;

    @OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reports> reports = new ArrayList<>();

    @OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Logs> logs = new ArrayList<>();



}
