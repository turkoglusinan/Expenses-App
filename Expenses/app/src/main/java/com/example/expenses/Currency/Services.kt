package com.example.expenses.Currency

import retrofit2.http.GET


interface Services {
    @GET("latest")
    suspend fun getPost(): CurrencyModel
}