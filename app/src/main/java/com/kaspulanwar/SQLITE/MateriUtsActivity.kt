package com.kaspulanwar.SQLITE

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_materi_uts.*

class MateriUtsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_uts)



        lik.setOnClickListener {
            val intent = Intent(this@MateriUtsActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)
        }


        uts.fromAsset("buku.pdf")
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
                Toast.makeText(this@MateriUtsActivity,"erro while opening page"+ page, Toast.LENGTH_SHORT).show()
                Log.d("Erro", ""+t.localizedMessage)
            }

            .onTap { false }
            .enableAnnotationRendering(true)
            .load()
    }

    }
