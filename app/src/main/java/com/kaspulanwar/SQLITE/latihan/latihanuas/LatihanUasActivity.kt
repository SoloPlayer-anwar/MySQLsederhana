package com.kaspulanwar.SQLITE.latihan.latihanuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import kotlinx.android.synthetic.main.activity_latihan_bab2.kembali
import kotlinx.android.synthetic.main.activity_latihan_uas.*

class LatihanUasActivity : AppCompatActivity() {

    var radiogr1: RadioGroup? = null
    var radiogr2: RadioGroup? = null
    var radiogr3: RadioGroup? = null
    var radiogr4: RadioGroup? = null
    var radiogr5: RadioGroup? = null
    var radiogr6: RadioGroup? = null
    var radiogr7: RadioGroup? = null
    var radiogr8: RadioGroup? = null
    var radiogr9: RadioGroup? = null
    var radiogr10: RadioGroup? = null

    private var proses: Button? = null
    private var value_no1: RadioButton? = null
    private var value_no2: RadioButton? = null
    private var value_no3: RadioButton? = null
    private var value_no4: RadioButton? = null
    private var value_no5: RadioButton? = null
    private var value_no6: RadioButton? = null
    private var value_no7: RadioButton? = null
    private var value_no8: RadioButton? = null
    private var value_no9: RadioButton? = null
    private var value_no10: RadioButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan_uas)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val liner2 = findViewById<View>(R.id.linearLayout2) as LinearLayout
        liner2.startAnimation(trans)

        radiogr1 = findViewById(R.id.radiogroup1)
        radiogr2 = findViewById(R.id.radiogroup2)
        radiogr3 = findViewById(R.id.radiogroup3)
        radiogr4 = findViewById(R.id.radiogroup4)
        radiogr5 = findViewById(R.id.radiogroup5)
        radiogr6 = findViewById(R.id.radiogroup6)
        radiogr7 = findViewById(R.id.radiogroup7)
        radiogr8 = findViewById(R.id.radiogroup8)
        radiogr9 = findViewById(R.id.radiogroup9)
        radiogr10 = findViewById(R.id.radiogroup10)

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

            val  pointf = radiogr6!!.getCheckedRadioButtonId()
            value_no6 = findViewById<View>(pointf) as RadioButton

            val  pointg = radiogr7!!.getCheckedRadioButtonId()
            value_no7 = findViewById<View>(pointg) as RadioButton

            val  pointh = radiogr8!!.getCheckedRadioButtonId()
            value_no8 = findViewById<View>(pointh) as RadioButton

            val  pointi = radiogr9!!.getCheckedRadioButtonId()
            value_no9 = findViewById<View>(pointi) as RadioButton

            val  pointj = radiogr10!!.getCheckedRadioButtonId()
            value_no10 = findViewById<View>(pointj) as RadioButton

            var nilai = 0 // variabel untuk menampung nilai
            if (value_no1!!.text.toString().toLowerCase() == "membuat database baru") {
                nilai = nilai + 10
            }
            if (value_no2!!.text.toString().toLowerCase() == "menampilkan semua database") {
                nilai = nilai + 10
            }
            if (value_no3!!.text.toString().toLowerCase() == "membuka atau mengaktifkan database yang bernama uniska") {
                nilai = nilai + 10
            }
            if (value_no4!!.text.toString().toLowerCase() == "membuat table mahasiswa") {
                nilai = nilai + 10
            }
            if (value_no5!!.text.toString().toLowerCase() == "show tables;") {
                nilai = nilai + 10
            }

            if (value_no6!!.text.toString().toLowerCase() == "desc mahasiswa;") {
                nilai = nilai + 10
            }

            if (value_no7!!.text.toString().toLowerCase() == "mengisi data di dalam table mahasiswa") {
                nilai = nilai + 10
            }

            if (value_no8!!.text.toString().toLowerCase() == "mengubah data didalam table mahasiswa") {
                nilai = nilai + 10
            }

            if (value_no9!!.text.toString().toLowerCase() == "select * from mahasiswa;") {
                nilai = nilai + 10
            }

            if (value_no10!!.text.toString().toLowerCase() == "rename table mahasiswa to fakultas") {
                nilai = nilai + 10
            }

            val i = Intent(this@LatihanUasActivity, ResultLatihanUasActivity::class.java)
            i.putExtra("nilai", nilai)
            startActivity(i)
            finish()
        })

        ga.setOnClickListener {
            val intent = Intent(this@LatihanUasActivity,
                HomeScreenActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }
}