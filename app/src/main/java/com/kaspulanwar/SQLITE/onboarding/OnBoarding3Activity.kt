package com.kaspulanwar.SQLITE.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_on_boarding3.*

class OnBoarding3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)

        val ob3 = findViewById<View>(R.id.onboarding3) as ImageView
        val title = findViewById<View>(R.id.solusi) as TextView
        val desc = findViewById<View>(R.id.desc) as TextView
        val btn = findViewById<View>(R.id.end) as Button


        ob3.startAnimation(trans)
        title.startAnimation(scale)
        desc.startAnimation(scale)
        btn.startAnimation(scale)

        end.setOnClickListener {
            val intent = Intent(this@OnBoarding3Activity,
            SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}