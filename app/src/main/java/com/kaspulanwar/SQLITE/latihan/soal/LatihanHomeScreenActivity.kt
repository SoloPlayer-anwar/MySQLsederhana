package com.kaspulanwar.SQLITE.latihan.soal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.latihan.*
import kotlinx.android.synthetic.main.activity_latihan_home_screen.*

class LatihanHomeScreenActivity : AppCompatActivity() {

    private var onPressedTime : Long = 0
    private var backToast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan_home_screen)

        val set = AnimationUtils.loadAnimation(this,R.anim.set)
        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val liner = findViewById<View>(R.id.linearLayout2) as LinearLayout
        val bab1 = findViewById<View>(R.id.bab1) as Button
        val bab2 = findViewById<View>(R.id.bab2) as Button
        val bab3 = findViewById<View>(R.id.bab3) as Button
        val bab4 = findViewById<View>(R.id.bab4) as Button
        val bab5 = findViewById<View>(R.id.bab5) as Button
        val bab6 = findViewById<View>(R.id.bab6) as Button

        liner.startAnimation(set)
        bab1.startAnimation(trans)
        bab2.startAnimation(trans)
        bab3.startAnimation(trans)
        bab4.startAnimation(trans)
        bab5.startAnimation(trans)
        bab6.startAnimation(trans)




        bab1.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanActivity::class.java)
            startActivity(intent)
            finish()
        }

        back.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        bab2.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanBab2Activity::class.java)
            startActivity(intent)
            finish()
        }

        bab3.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanBab3Activity::class.java)
            startActivity(intent)
            finish()
        }

        bab4.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanBab4Activity::class.java)
            startActivity(intent)
            finish()
        }

        bab5.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanBab5Activity::class.java)
            startActivity(intent)
            finish()
        }

        bab6.setOnClickListener {
            val intent = Intent(this@LatihanHomeScreenActivity,
            LatihanBab6Activity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        if (onPressedTime + 2000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext, "Tekan 2x Untuk Keluar ",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        onPressedTime = System.currentTimeMillis()

    }
}
