package com.expense.split.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CATEGORY_TBL")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
