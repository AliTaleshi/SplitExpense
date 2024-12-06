package com.expense.split.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TBL")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
