package com.example.expenses.fragments.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY expense_id ASC")
    fun readAllData(): LiveData<List<Expense>>


}