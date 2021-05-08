package com.example.expenses.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.expenses.CurrencyConverter.CurrencyConverter
import com.example.expenses.R
import com.example.expenses.databinding.FragmentRecyclerItemBinding
import com.example.expenses.fragments.Data.Expense
import kotlinx.android.synthetic.main.fragment_recycler_item.view.*

class ListAdapter(val context: Context, val base: String): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class MyViewHolder(val binding: FragmentRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(FragmentRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]

        val converter = CurrencyConverter(context)

        holder.binding.riTextView.text = currentItem.explanation
        holder.binding.riMoneyView.text = String.format("%.2f", converter.convert(currentItem.currency, base, currentItem.expense))+" "+ when(base){
            "TRY" -> "₺"
            "GBP" -> "£"
            "EUR" -> "€"
            "USD" -> "$"
            else -> "₺"
        }
        holder.binding.riIcon.setImageResource(
            when(currentItem.type){
                0 -> R.drawable.bill_icon
                1 -> R.drawable.rent_icon
                2 -> R.drawable.other_icon
                else -> R.drawable.other_icon
            }
        )
        holder.binding.riIcon.setColorFilter(
            ContextCompat.getColor(holder.itemView.context,
            when(currentItem.type) {
                0 -> R.color.orange
                1 -> R.color.colorLightGreen
                2 -> R.color.colorDarkGreen
                else -> R.color.darkOrange
            }
        ), android.graphics.PorterDuff.Mode.SRC_IN)

        holder.itemView.recycler_item_id.setOnClickListener{
            val action = MainPageDirections.actionMainPageToDeleteFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }
}

