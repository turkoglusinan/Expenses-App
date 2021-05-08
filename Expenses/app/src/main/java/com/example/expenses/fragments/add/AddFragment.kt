package com.example.expenses.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expenses.R
import com.example.expenses.fragments.Data.Expense
import com.example.expenses.fragments.Data.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val explanation = add_exp.text.toString()
        val expenses = add_expense.text.toString()
        val typeRGroup = RChoice
        val currencyRGroup = RMoney

        if (inputCheck(
                explanation,
                expenses,
                typeRGroup.checkedRadioButtonId.toString(),
                currencyRGroup.checkedRadioButtonId.toString()
            )
        ) {
            var type: Int = 2
            when (typeRGroup.checkedRadioButtonId) {
                R.id.Bill -> type = 0
                R.id.Rent -> type = 1
                R.id.Other -> type = 2
            }
            var currency: String = "GBP"
            when (currencyRGroup.checkedRadioButtonId) {
                R.id.TL -> currency = "TRY"
                R.id.USD -> currency = "USD"
                R.id.EURO -> currency = "EUR"
                R.id.GBP -> currency = "GBP"
            }
            //Create User Object
            val spending =
                Expense( explanation, expenses.toString().toDouble(), type, currency)
            //add data to database
            mExpenseViewModel.addExpense(spending)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_mainPage)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(
        explanation: String,
        expenses: String,
        type: String,
        currency: String
    ): Boolean {
        return !(TextUtils.isEmpty(explanation) && TextUtils.isEmpty(expenses) && TextUtils.isEmpty(type) && TextUtils.isEmpty(currency))
    }
}