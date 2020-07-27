package com.kaspulanwar.SQLITE.babvideo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import kotlinx.android.synthetic.main.activity_bab_video.*

class BabVideoActivity : AppCompatActivity() {

    private var backPressedTime : Long = 0
    private var backToast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bab_video)

        val translate = AnimationUtils.loadAnimation(this,R.anim.translate)
        val set = AnimationUtils.loadAnimation(this,R.anim.set)
        val linear = findViewById<View>(R.id.linearLayout2) as LinearLayout
        val bab1 =findViewById<View>(R.id.bab1) as Button
        val bab2 =findViewById<View>(R.id.bab2) as Button
        val bab3 =findViewById<View>(R.id.bab3) as Button
        val bab4 =findViewById<View>(R.id.bab4) as Button
        val bab5 =findViewById<View>(R.id.bab5) as Button
        val bab6 =findViewById<View>(R.id.bab6) as Button


        linear.startAnimation(set)
        bab1.startAnimation(translate)
        bab2.startAnimation(translate)
        bab3.startAnimation(translate)
        bab4.startAnimation(translate)
        bab5.startAnimation(translate)
        bab6.startAnimation(translate)


        back.setOnClickListener {
            val intent  = Intent(this@BabVideoActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }


        bab1.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=87GPDbulNpw&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=6"))
            startActivity(i)
        }

        bab2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AbzwECWTY5c&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=5"))
            startActivity(intent)
        }


        bab3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=emJDkPwjaxk&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=4"))
            startActivity(intent)
        }

        bab4.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=To4bGvJAP_o&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=3"))
            startActivity(intent)
        }

        bab5.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HZVeLVklW3I&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=2"))
            startActivity(intent)
        }

        bab6.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=oAVQZL0szVo&list=UU3Jn_xc2D6l_jXMjylzjg0Q&index=1"))
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext, "Tekan 2x Untuk Keluar ",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        backPressedTime = System.currentTimeMillis()
    }
}
