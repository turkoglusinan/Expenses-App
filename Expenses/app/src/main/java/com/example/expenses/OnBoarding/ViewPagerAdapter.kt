package com.example.expenses.OnBoarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.expenses.OnBoarding.CarouselPage
import com.example.expenses.OnBoarding.CarouselPageFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private var pages = mutableListOf<CarouselPage>()

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = CarouselPageFragment(pages[position])

    fun setPages(pages: List<CarouselPage>) {
        this.pages.addAll(pages)
    }
}