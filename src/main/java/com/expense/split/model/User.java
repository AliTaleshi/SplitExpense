package com.expense.split.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    @CreationTimestamp
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private List<Expense> paidExpenses;

    @ManyToMany(mappedBy = "debtors")
    private List<Expense> debtorExpenses;
}
