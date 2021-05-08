package com.example.expenses.OnBoarding

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.expenses.R
import com.google.android.material.tabs.TabLayoutMediator

class CarouselPager(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attributeSet, defStyleAttr) {
    constructor(context: Context, attributeSet: AttributeSet? = null) : this(
        context,
        attributeSet,
        0
    )

    constructor(context: Context) : this(context, null)

    private val zoomInAnim = AnimationUtils.loadAnimation(context, R.anim.bottom_animation)
    private val carouselPages = mutableListOf<CarouselPage>()
    private var carouselListener: CarouselListener? = null

    init {
        inflateView()
        setUpListeners()
    }

    private fun inflateView() {
        View.inflate(context, R.layout.carousel_pager, this)
    }

    private fun setUpListeners() {
        findViewById<TextView>(R.id.btnGetStarted)
            .setOnClickListener {
                carouselListener?.onCarouselFinished()
            }
        findViewById<TextView>(R.id.btnSkip)
            .setOnClickListener {
                carouselListener?.onCarouselFinished(true)
            }
    }

    fun setUpCarousel(
        fragmentActivity: FragmentActivity,
        carouselPages: List<CarouselPage>
    ): CarouselPager {
        val viewPagerAdapter = ViewPagerAdapter(fragmentActivity)
        viewPagerAdapter.setPages(carouselPages)


        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == carouselPages.size - 1) {

                    findViewById<TextView>(R.id.btnGetStarted).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.btnGetStarted).startAnimation(zoomInAnim)
                    findViewById<TextView>(R.id.btnSkip).visibility = View.GONE
                } else {
                    findViewById<TextView>(R.id.btnGetStarted).visibility = View.GONE
                    findViewById<TextView>(R.id.btnSkip).visibility = View.VISIBLE
                }
            }
        })


        TabLayoutMediator(findViewById(R.id.tabLayout), viewPager) { _, _ -> }.attach()

        this.carouselPages.addAll(carouselPages)
        return this
    }

    fun setUpPageTransformer(pageTransformer: ViewPager2.PageTransformer): CarouselPager {
        findViewById<ViewPager2>(R.id.viewPager).setPageTransformer(pageTransformer)
        return this
    }

    fun setUpCarouselListener(carouselListener: CarouselListener): CarouselPager {
        this.carouselListener = carouselListener
        return this
    }

    interface CarouselListener {
        fun onCarouselFinished(skipped: Boolean = false)
    }

}