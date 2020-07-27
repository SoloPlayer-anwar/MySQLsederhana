package com.kaspulanwar.SQLITE.profile

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    internal lateinit var btn : TextView
    internal lateinit var btn1 : TextView
    internal lateinit var btn3 : TextView
    internal lateinit var myDialog: Dialog
    internal lateinit var myDialog1 : Dialog
    internal lateinit var myDialog3: Dialog
    internal lateinit var txt : ImageView
    internal lateinit var txt1 : ImageView
    internal lateinit var txt3 : ImageView

    private var backPressedTime: Long = 0
    private var backToast : Toast? = null

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn = findViewById<View>(R.id.tv_my_wallet) as TextView
        btn1 = findViewById<View>(R.id.tv_edit_profile) as TextView
        btn3 = findViewById<View>(R.id.tv_edit_profile3) as TextView
        btn.setOnClickListener{
            ShowDialog()
        }

        btn1.setOnClickListener{
            ShowDialog1()
        }



        btn3.setOnClickListener{
            ShowDialog3()
        }

        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)
        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)

        val text1 = findViewById<View>(R.id.setting) as TextView
        val text2 = findViewById<View>(R.id.iv_profile) as ImageView
        val text3 = findViewById<View>(R.id.iv_nama) as TextView
        val text4 = findViewById<View>(R.id.tv_email) as TextView
        val text5 = findViewById<View>(R.id.tv_my_wallet) as TextView
        val text6 = findViewById<View>(R.id.tv_edit_profile) as TextView
        val text7 = findViewById<View>(R.id.tv_edit_profile2) as TextView
        val text8 = findViewById<View>(R.id.tv_edit_profile3 ) as TextView
        val text9 = findViewById<View>(R.id.facebook) as TextView
        val button = findViewById<View>(R.id.beranda) as TextView

        text1.startAnimation(trans)
        text2.startAnimation(scale)
        text3.startAnimation(trans)
        text4.startAnimation(trans)
        text5.startAnimation(trans)
        text6.startAnimation(trans)
        text7.startAnimation(trans)
        text8.startAnimation(trans)
        button.startAnimation(scale)
        text9.startAnimation(trans)

        preferences = Preferences(this)

        iv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        beranda.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://web.telegram.org/#/im"))
            startActivity(i)
        }

        back.setOnClickListener {
            val intent = Intent(this@ProfileActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        facebook.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/m.Kaspul.Anwar"))
            startActivity(i)
        }

        tv_edit_profile2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/muhammad.kaspul.anwar/"))
            startActivity(i)
        }


    }

     fun  ShowDialog() {
        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.developer_activity)
        myDialog.setTitle("Ok Selamat Belajar "+preferences.getValues("nama"))

        txt = myDialog.findViewById<View>(R.id.close) as ImageView
        txt.isEnabled = true
        txt.setOnClickListener{
            myDialog.cancel()
        }

        myDialog.show()

    }

    fun  ShowDialog1() {
        myDialog1 = Dialog(this)
        myDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog1.setContentView(R.layout.bahasa_activity)
        myDialog1.setTitle("Untuk Sementara Bahasa Cuma 1 Dulu Maaf "+preferences.getValues("nama"))

        txt1 = myDialog1.findViewById<View>(R.id.close) as ImageView
        txt1.isEnabled = true
        txt1.setOnClickListener{
            myDialog1.cancel()
        }

        myDialog1.show()

    }



    fun  ShowDialog3() {
        myDialog3 = Dialog(this)
        myDialog3.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog3.setContentView(R.layout.bantuan_activity)
        myDialog3.setTitle("Bantuan Untuk Request Foto Profile "+preferences.getValues("nama"))

        txt3 = myDialog3.findViewById<View>(R.id.close) as ImageView
        txt3.isEnabled = true
        txt3.setOnClickListener{
            myDialog3.cancel()
        }

        myDialog3.show()

    }

    override fun onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext,"Tekan tombol back di atas untuk kembali ",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        backPressedTime = System.currentTimeMillis()


    }


}


