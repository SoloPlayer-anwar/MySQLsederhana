package com.kaspulanwar.SQLITE.latihan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.latihan.soal.LatihanHomeScreenActivity
import kotlinx.android.synthetic.main.activity_latihan_bab2.*

class LatihanBab2Activity : AppCompatActivity() {

    private  var onPressedBack : Long = 0
    private var backToast : Toast? = null

    var radiogr1: RadioGroup? = null
    var radiogr2: RadioGroup? = null
    var radiogr3: RadioGroup? = null
    var radiogr4: RadioGroup? = null
    var radiogr5: RadioGroup? = null

    private var proses: Button? = null
    private var value_no1: RadioButton? = null
    private var value_no2: RadioButton? = null
    private var value_no3: RadioButton? = null
    private var value_no4: RadioButton? = null
    private var value_no5: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan_bab2)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)

        val gambar = findViewById<View>(R.id.gambar) as TextView
        val dasar = findViewById<View>(R.id.dasar) as ImageView

        gambar.startAnimation(trans)
        dasar.startAnimation(trans)

        radiogr1 = findViewById(R.id.radiogroup1)
        radiogr2 = findViewById(R.id.radiogroup2)
        radiogr3 = findViewById(R.id.radiogroup3)
        radiogr4 = findViewById(R.id.radiogroup4)
        radiogr5 = findViewById(R.id.radiogroup5)
        proses = findViewById(R.id.button_proses)
        proses!!.setOnClickListener(View.OnClickListener {

            val pointa = radiogr1!!.getCheckedRadioButtonId()
            value_no1 = findViewById<View>(pointa) as RadioButton
            val pointb = radiogr2!!.getCheckedRadioButtonId()
            value_no2 = findViewById<View>(pointb) as RadioButton
            val pointc = radiogr3!!.getCheckedRadioButtonId()
            value_no3 = findViewById<View>(pointc) as RadioButton
            val pointd = radiogr4!!.getCheckedRadioButtonId()
            value_no4 = findViewById<View>(pointd) as RadioButton
            val pointe = radiogr5!!.getCheckedRadioButtonId()
            value_no5 = findViewById<View>(pointe) as RadioButton
            var nilai = 0 // variabel untuk menampung nilai
            if (value_no1!!.text.toString().toLowerCase() == "structured query language") {
                nilai = nilai + 20
            }
            if (value_no2!!.text.toString().toLowerCase() == "create alter rename dan drop") {
                nilai = nilai + 20
            }
            if (value_no3!!.text.toString().toLowerCase() == "create database penjualan;") {
                nilai = nilai + 20
            }
            if (value_no4!!.text.toString().toLowerCase() == "menampilkan semua database") {
                nilai = nilai + 20
            }
            if (value_no5!!.text.toString().toLowerCase() == "use nama databasenya;") {
                nilai = nilai + 20
            }
            val i = Intent(this@LatihanBab2Activity, ResultBab2Activity::class.java)
            i.putExtra("nilai", nilai)
            startActivity(i)
            finish()
        })

        kembali.setOnClickListener {
            val intent = Intent(this@LatihanBab2Activity,
                LatihanHomeScreenActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }

    override fun onBackPressed() {
        if (onPressedBack + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }
        else {
            backToast = Toast.makeText(baseContext, "Tekan tombol back di atas",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        onPressedBack = System.currentTimeMillis()
    }
}
