package com.example.expenses.fragments.delete

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expenses.R
import com.example.expenses.fragments.Data.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_delete.view.*
import kotlinx.android.synthetic.main.fragment_recycler_item.view.*


class DeleteFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel
    lateinit var v: View
    private val args by navArgs<DeleteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_delete, container, false)
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        v.delete_explanation.text = args.expenseArgs.explanation
        v.delete_expense.text = args.expenseArgs.expense.toString()+" "+when(args.expenseArgs.currency){
                "TRY" -> "₺"
                "GBP" -> "£"
                "EUR" -> "€"
                "USD" -> "$"
                else -> "₺"
            }

        v.delete_icon.setImageResource(
            when(args.expenseArgs.type){
                0 -> R.drawable.bill_icon
                1 -> R.drawable.rent_icon
                2 -> R.drawable.other_icon
                else -> R.drawable.other_icon
            }
        )
        v.delete_icon.setColorFilter(
            ContextCompat.getColor(v.context,
                when(args.expenseArgs.type) {
                    0 -> R.color.orange
                    1 -> R.color.colorLightGreen
                    2 -> R.color.colorDarkGreen
                    else -> R.color.darkOrange
                }
            ), android.graphics.PorterDuff.Mode.SRC_IN)

        v.delete_button.setOnClickListener{deleteUser()}
        v.delete_back.setOnClickListener{findNavController().navigate(R.id.action_deleteFragment_to_mainPage)}
        return v
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mExpenseViewModel.deleteExpense(args.expenseArgs)
            findNavController().navigate(R.id.action_deleteFragment_to_mainPage)
        }

        builder.setNeutralButton("No"){ _, _ ->}
        builder.setTitle("Delete ${args.expenseArgs.explanation}?")
        builder.setMessage("Are you sure you want to delete ${args.expenseArgs.explanation}?")
        builder.create().show()
        }

}