package com.example.expenses.Currency



class Repository {

    suspend fun getPost(): CurrencyModel{
         return RetrofitInstance.api.getPost()

    }
}