package com.example.expenses.fragments.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Expense>>
    private val repository: ExpenseRepository

    init {
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        readAllData = repository.readAllData
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }
}