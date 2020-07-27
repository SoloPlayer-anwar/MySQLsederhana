package com.kaspulanwar.SQLITE.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signin.SignInActivity
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding1.*

class Onboarding1Activity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)
        val text1 = findViewById<View>(R.id.awal) as TextView
        val text2 = findViewById<View>(R.id.desc) as TextView
        val gambar = findViewById<View>(R.id.ob1) as ImageView


        text1.startAnimation(trans)
        text2.startAnimation(trans)
        gambar.startAnimation(scale)


        preferences = Preferences(this)

//
        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@Onboarding1Activity,
                SignInActivity::class.java)
            startActivity(intent)
        }



        lanjut.setOnClickListener {
            val intent = Intent(this@Onboarding1Activity,
            OnboardingTwoActivity::class.java)
            startActivity(intent)
            finish()
        }

        skipall.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()

            val intent = Intent(this@Onboarding1Activity,
                SignInActivity::class.java)
            startActivity(intent)
        }

    }

}