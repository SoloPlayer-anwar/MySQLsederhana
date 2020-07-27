package com.kaspulanwar.SQLITE

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kaspulanwar.SQLITE.Location.LocationUserActivity
import com.kaspulanwar.SQLITE.babvideo.BabVideoActivity
import com.kaspulanwar.SQLITE.latihan.latihanuas.LatihanUasActivity
import com.kaspulanwar.SQLITE.latihan.soal.LatihanHomeScreenActivity
import com.kaspulanwar.SQLITE.profile.ProfileActivity
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {

    lateinit var preferences : Preferences
    lateinit var mDatabase : DatabaseReference



    private var backPressedTime: Long = 0
    private var backToast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val slide  = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        val scale  = AnimationUtils.loadAnimation(this,R.anim.scale)
        val translate = AnimationUtils.loadAnimation(this,R.anim.translate)
        val materi_teks = findViewById<View>(R.id.materi_teks) as LinearLayout
        val teks_uas = findViewById<View>(R.id.teks_uas) as Button
        val rekomen = findViewById<View>(R.id.rekomen) as Button
        val video_uas = findViewById<View>(R.id.video_uas) as Button
        val latiha_uas = findViewById<View>(R.id.latihan_uas )as Button
        val profile = findViewById<View>(R.id.profile) as LinearLayout
        val latihanwara = findViewById<View>(R.id.latihanwara) as LinearLayout
        val videowara = findViewById<View>(R.id.videowara) as LinearLayout
        val linearlayaout = findViewById<View>(R.id.linearLayout2) as LinearLayout
        val iv_profile = findViewById<View>(R.id.iv_profile) as ImageView
        val text1 = findViewById<View>(R.id.et_nama) as TextView
        val text2 = findViewById<View>(R.id.et_gmail) as TextView

        linearlayaout.startAnimation(slide)
        iv_profile.startAnimation(scale)
        text1.startAnimation(translate)
        text2.startAnimation(translate)
        materi_teks.startAnimation(scale)
        videowara.startAnimation(scale)
        latihanwara.startAnimation(scale)
        profile.startAnimation(scale)
        teks_uas.startAnimation(translate)
        video_uas.startAnimation(translate)
        latiha_uas.startAnimation(translate)
        rekomen.startAnimation(scale)


        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        mDatabase = FirebaseDatabase.getInstance().getReference("Buku")

        et_nama.text = preferences.getValues("nama")
        et_gmail.text = preferences.getValues("email")


        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        Log.v("tamvan", "url "+preferences.getValues("url"))



        videowara.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            BabVideoActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        materi_teks.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            MateriTeksActivity::class.java)
            startActivity(intent)
        }


        latihanwara.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            LatihanHomeScreenActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        video_uas.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fxe6qev-bno"))
            startActivity(i)
        }

        profile.setOnClickListener{
            val intent = Intent(this@HomeScreenActivity,
            ProfileActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        latihan_uas.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            LatihanUasActivity::class.java)
            startActivity(intent)
        }

        rekomen.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://achmatim.net/"))
            startActivity(i)
        }

        user.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            LocationUserActivity::class.java)
            startActivity(intent)
            finish()
        }


        teks_uas.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity,
            MateriUtsActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Dari Hal 35 Sampai 50 Bagian Dasar Dasar SQL",Toast.LENGTH_LONG).show()

        }

    }

    override fun onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext,"Tekan 2x untuk Keluar ",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        backPressedTime = System.currentTimeMillis()
    }
}
