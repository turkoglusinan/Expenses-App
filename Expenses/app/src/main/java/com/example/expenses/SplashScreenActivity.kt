package com.example.expenses
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.expenses.OnBoarding.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val bgImg : ImageView = findViewById(R.id.iv_splashScreen)
        val appText : TextView = findViewById(R.id.ss_textView)
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val slideAnim2 = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        bgImg.startAnimation(slideAnim)
        appText.startAnimation(slideAnim2)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 1500)

    }
}