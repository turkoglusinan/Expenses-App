package com.example.expenses.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expenses.R
import com.example.expenses.fragments.Data.Expense
import kotlinx.android.synthetic.main.fragment_recycler_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.itemView.riTextView.text = currentItem.explanation
        holder.itemView.riMoneyView.text = currentItem.expense.toString()
        holder.itemView.riIcon.setImageResource(
            when(currentItem.type){
                0 -> R.drawable.bill_icon
                1 -> R.drawable.rent_icon
                2 -> R.drawable.other_icon
                else -> R.drawable.other_icon
            }
        )
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }
}

