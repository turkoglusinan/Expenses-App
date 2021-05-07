package com.example.expenses.fragments.Data

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()

    suspend fun addExpense(expense: Expense) {
        expenseDao.addExpense(expense)
    }

}