package com.example.expenses.fragments.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "expense_table")
data class Expense(

    val explanation: String,
    val expense: Double,
    val type: Int,
    val currency: String,
    @PrimaryKey(autoGenerate = true)
    val expense_id: Int = 0

):Parcelable
