package com.example.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class CarouselPageFragment(private val page: CarouselPage) : Fragment(R.layout.fragment_carousel_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.carouselPageImage).setImageResource(page.image)
        view.findViewById<TextView>(R.id.carouselPageTitle).setText(page.title)
        view.findViewById<TextView>(R.id.carouselPageText).setText(page.text)

    }
}