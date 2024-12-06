package com.expense.split.service.impl;

import com.expense.split.model.Expense;
import com.expense.split.repository.ExpenseRepository;
import com.expense.split.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Transactional
    @Override
    public List<Expense> getExpensesWithDebtors() {
        List<Expense> expenses = expenseRepository.findAll();
        for (Expense expense : expenses) {
            expense.getDebtors().size(); // Force initialization
        }
        return expenses;
    }

}
