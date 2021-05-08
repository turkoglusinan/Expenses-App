package com.example.expenses.OnBoarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.expenses.Home
import com.example.expenses.R
import com.example.expenses.ZoomOutPageTransformer

class MainActivity : AppCompatActivity(), CarouselPager.CarouselListener {

    lateinit var preferences: SharedPreferences
    val pref_show_onBoard = "onBoarding"
    val list : MutableList<Fragment> = ArrayList()


    private val carouselPages = listOf(
            CarouselPage(R.drawable.carousel, R.string.onBoarding1_title, R.string.onBoarding1_text),
            CarouselPage(R.drawable.carousel, R.string.onBoarding2_title, R.string.onBoarding2_text),
            CarouselPage(R.drawable.carousel, R.string.onBoarding3_title, R.string.onBoarding3_text)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBoardingPass()

        findViewById<CarouselPager>(R.id.carouselPager)
                .setUpCarousel(this, carouselPages)
                .setUpPageTransformer(ZoomOutPageTransformer())
                .setUpCarouselListener(this)
    }

    override fun onCarouselFinished(skipped: Boolean) {
        startActivity(Intent(applicationContext, Home::class.java))
        finish()
        val carouselStatus = if (skipped) "skipped" else "completed"
        Toast.makeText(this, "You've $carouselStatus the on boarding.", Toast.LENGTH_SHORT).show()
    }

    fun onBoardingPass(){
        val prefences = getSharedPreferences("onBoardingSlider", Context.MODE_PRIVATE)
        if(!prefences.getBoolean(pref_show_onBoard, true)){
            startActivity(Intent(applicationContext, Home::class.java))
            finish()
        }
        val editor = prefences.edit()
        editor.putBoolean(pref_show_onBoard, false)
        editor.apply()
    }
}