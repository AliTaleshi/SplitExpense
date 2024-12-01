package com.expense.split.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXPENSE")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Double amount;
    @CreationTimestamp
    private LocalDateTime date;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
