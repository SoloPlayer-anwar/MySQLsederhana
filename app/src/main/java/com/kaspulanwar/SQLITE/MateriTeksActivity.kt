package com.kaspulanwar.SQLITE

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_materi_teks.*

class MateriTeksActivity : AppCompatActivity() {


    private var backPressedTime: Long = 0
    private var backToast : Toast? = null

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_teks)

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val view6 = findViewById<View>(R.id.view6) as View

        view6.startAnimation(trans)


        back.setOnClickListener {
            val intent = Intent(this@MateriTeksActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)
        }


        pdfview.fromAsset("buku.pdf")
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onDraw { canvas, pageWidth, pageHeight, displayedPage ->

            }.onDraw { canvas, pageWidth, pageHeight, displayedPage ->

            }

            .onPageChange { page, pageCount ->

            }.onPageError { page, t ->
                Toast.makeText(this@MateriTeksActivity,"erro while opening page"+ page, Toast.LENGTH_SHORT).show()
                Log.d("Erro", ""+t.localizedMessage)
            }

            .onTap { false }
            .enableAnnotationRendering(true)
            .load()
    }

    override fun onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext,"Tekan tombol di atas"+preferences.getValues("nama"),Toast.LENGTH_LONG)
            backToast!!.show()
        }

        backPressedTime = System.currentTimeMillis()
    }
}