package com.example.expenses.OnBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expenses.Home
import com.example.expenses.R
import com.example.expenses.ZoomOutPageTransformer

class MainActivity : AppCompatActivity(), CarouselPager.CarouselListener {

    private val carouselPages = listOf(
            CarouselPage(R.drawable.carousel, R.string.onBoarding1_title, R.string.onBoarding1_text),
            CarouselPage(R.drawable.carousel, R.string.onBoarding2_title, R.string.onBoarding2_text),
            CarouselPage(R.drawable.carousel, R.string.onBoarding3_title, R.string.onBoarding3_text)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}