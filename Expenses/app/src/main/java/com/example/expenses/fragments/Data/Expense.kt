package com.example.expenses.fragments.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense_table")
data class Expense(

    val explanation: String,
    val expense: Int,
    val type: Int,
    val currency: String,
    @PrimaryKey(autoGenerate = true)
    val expense_id: Int = 0

)
