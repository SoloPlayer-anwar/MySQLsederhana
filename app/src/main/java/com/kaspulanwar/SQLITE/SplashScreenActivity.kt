package com.kaspulanwar.SQLITE

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.kaspulanwar.SQLITE.onboarding.Onboarding1Activity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val rotate = AnimationUtils.loadAnimation(this,R.anim.set)
        val splash = findViewById<View>(R.id.splash) as ImageView

        splash.startAnimation(rotate)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity,
                Onboarding1Activity::class.java)
            startActivity(intent)
            finish()
        }, 4000)

    }
}
