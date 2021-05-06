package com.example.expenses.fragments.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.expenses.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_main_page.view.*

class MainPage : Fragment() {

    val NAME = "Name"
    val GENDER = "Gender"
    val FILE = "com.example.expenses"
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main_page, container, false)
        val name = v.ah_name
        v.ah_floatingaddbtn.setOnClickListener{
            findNavController().navigate(R.id.action_mainPage_to_addFragment)
        }

        name.setOnClickListener {
            nameChange()
        }
        return v
    }

    fun HName() {
        val preferences = requireActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE)
        val nameText: TextView = v.findViewById<TextView>(R.id.ah_name)
        if (preferences.getInt(GENDER, 0) == 1) {
            nameText.text = "Sir " + preferences.getString(NAME, "Sinan")
        } else if (preferences.getInt(GENDER, 0) == 2) {
            nameText.text = "Lady " + preferences.getString(NAME, "Dilara")
        } else {
            nameText.text = preferences.getString(NAME, "Deniz")
        }

    }

    fun nameChange() {
        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.namechange, null)
        val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialogView).show()
        val preferences = requireActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val nameText: TextInputEditText = mDialogView.findViewById(R.id.nc_name)
        val radioGroup: RadioGroup = mDialogView.findViewById(R.id.Rgroup)

        mDialogView.findViewById<Button>(R.id.nc_save).setOnClickListener {
            editor.putString(NAME, nameText.text.toString())
            if (radioGroup.checkedRadioButtonId == R.id.Male)
                editor.putInt(GENDER, 1)
            else if (radioGroup.checkedRadioButtonId == R.id.Female)
                editor.putInt(GENDER, 2)
            else
                editor.putInt(GENDER, 0)
            editor.apply()
            mBuilder.dismiss()
            HName()
        }
        mDialogView.findViewById<Button>(R.id.nc_cancel).setOnClickListener {
            mBuilder.dismiss()
        }
    }


}