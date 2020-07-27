package com.kaspulanwar.SQLITE.onboarding

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)
        val text1 = findViewById<View>(R.id.title) as TextView
        val text2 = findViewById<View>(R.id.desc) as TextView
        val gambar = findViewById<View>(R.id.ob2) as ImageView


        text1.startAnimation(trans)
        text2.startAnimation(trans)
        gambar.startAnimation(scale)



        lanjutkan.setOnClickListener {
            val intent = Intent(this@OnboardingTwoActivity,
            OnBoarding3Activity::class.java)
            startActivity(intent)
        }


        }

    }

