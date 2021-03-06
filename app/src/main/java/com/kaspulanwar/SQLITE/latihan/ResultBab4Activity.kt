package com.kaspulanwar.SQLITE.latihan

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.latihan.soal.LatihanHomeScreenActivity
import kotlinx.android.synthetic.main.activity_result_bab4.*

class ResultBab4Activity : AppCompatActivity() {

    var txt_nilai: TextView? = null
    var txt_comment: TextView? = null
    var cobalagi: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_bab4)

        home.setOnClickListener {
            val intent = Intent(this@ResultBab4Activity,
            LatihanHomeScreenActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        val trans = AnimationUtils.loadAnimation(this,R.anim.translate)
        val txt = findViewById<View>(R.id.txt_nilai) as TextView
        val txt2 = findViewById<View>(R.id.txt_comment) as TextView

        txt.startAnimation(trans)
        txt2.startAnimation(trans)

        txt_nilai = findViewById(R.id.txt_nilai)
        txt_comment = findViewById(R.id.txt_comment)
        cobalagi = findViewById(R.id.btn_cobalagi)
        val nilai = intent.extras!!.getInt("nilai")
        txt_nilai!!.setText(nilai.toString())
        if (nilai >= 80) {

            home.visibility = View.VISIBLE
            notif.visibility = View.VISIBLE
            txt_comment!!.setText("Kamu lulus, dan mendapatkan Nilai Terbaik")

        } else if (nilai >= 60) {
            home.visibility = View.VISIBLE
            notif.visibility = View.VISIBLE
            txt_comment!!.setText("Selamat Kamu lulus")

        } else {
            txt_comment!!.setText("Kamu harus belajar lagi")
        }
        cobalagi!!.setOnClickListener(View.OnClickListener {
            val i = Intent(this@ResultBab4Activity, LatihanBab4Activity::class.java)
            startActivity(i)
            finish()
        })

        notif.setOnClickListener {
            showNotif()
        }

    }

    private fun showNotif() {
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
            .setContentTitle("Selamat Latihan Bab4 Clear")
            .setContentText("Silahkan klik disini jika anda mau melanjutkan materi bab5!")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
}
    }
