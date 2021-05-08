package com.example.expenses.Offline

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expenses.fragments.Data.Expense
import com.example.expenses.fragments.Data.ExpenseDao
import com.example.expenses.fragments.Data.ExpenseDatabase

@Database(entities = arrayOf(Expense::class), version = 1)
abstract class Network : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    //val db = Room.databaseBuilder(applicationContext,ExpenseDatabase::class.java, "database-name").build()

    //val expenseDao = db.expenseDao()
    //val expense: List<Expense> = expenseDao.getAll()

}