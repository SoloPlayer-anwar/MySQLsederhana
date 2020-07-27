package com.kaspulanwar.SQLITE.latihan.latihanuas

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import kotlinx.android.synthetic.main.activity_result_latihan_uas.*

class ResultLatihanUasActivity : AppCompatActivity() {

    var txt_nilai: TextView? = null
    var txt_komen: TextView? = null
    var coba: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_latihan_uas)

        val trans = AnimationUtils.loadAnimation(this, R.anim.translate)
        val scale = AnimationUtils.loadAnimation(this, R.anim.scale)

        val gambar = findViewById<View>(R.id.gambar) as ImageView
        val txtwarm = findViewById<View>(R.id.textwarm) as TextView
        val txtnilai = findViewById<View>(R.id.txt_nilai) as TextView
        val txtkomen = findViewById<View>(R.id.txt_komen) as TextView

        gambar.startAnimation(scale)
        txtwarm.startAnimation(trans)
        txtnilai.startAnimation(trans)
        txtkomen.startAnimation(scale)

        txt_nilai = findViewById(R.id.txt_nilai)
        txt_komen = findViewById(R.id.txt_komen)
        coba = findViewById(R.id.coba)

        var nilai = intent.extras!!.getInt("nilai")

        txt_nilai!!.setText(nilai.toString())

        if (nilai >= 80) {
            buka.visibility = View.VISIBLE
            txt_komen!!.setText("Kamu Lulus Dan,Mendapatkan Nilai Terbaik")

        } else if (nilai >= 60) {
            buka.visibility = View.VISIBLE
            txt_komen!!.setText("Selamat Kamu lulus Dengan nilai Kurang Memuaskan")

        } else {
            coba!!.visibility = View.VISIBLE
            txt_komen!!.setText("Kamu Harus Coba Lagi Gaes!")
        }

        coba!!.setOnClickListener {
            val intent = Intent(
                this@ResultLatihanUasActivity,
                LatihanUasActivity::class.java
            )
            startActivity(intent)
            finish()
        }

       buka.setOnClickListener {
           showNotif1()
       }

    }

    private fun showNotif1() {
        val NOTIFICATION_CHANNEL_ID = "channel_Belajar MySQL"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "Belajar MySQL notif"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this, CheckoutSuccessActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        mIntent.putExtras(bundle)

        val mIntent = Intent(this, HomeScreenActivity::class.java)
        val bundle = Bundle()
        mIntent.putExtras(bundle)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.mipmap.ic_launcher_round
                )
            )
            .setTicker("notif Belajar MySQL")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Selamat Latihan Uas Selesai")
            .setContentText("Ulang Terus Materi Jangan Lupa Eksekusi Di CMD!! Selamat Belajar")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())


    }

}