package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long accID;
    @Column(name = "costumer_id")
    private Long costumerID;
    @Column(name = "acc_name")
    private String accName;



    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reports> reportses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;



}