package com.expense.split.service;

import com.expense.split.model.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getExpensesWithDebtors();
}
