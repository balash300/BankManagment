package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;
    private String fname;
    private String lname;
    private String address;
    @Column(name = "contact_add")
    private String contactAdd;
    private String username;
    private String password;



    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactions = new ArrayList<>();



}
