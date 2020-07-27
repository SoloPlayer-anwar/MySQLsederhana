package com.kaspulanwar.SQLITE.sign.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signup.SignUpActivity
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    internal  lateinit var btn4 : TextView
    internal lateinit var myDialog : Dialog
    internal lateinit var image2 : ImageView

    lateinit var iUsername :String
    lateinit var iPassword :String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val Animasi4 = AnimationUtils.loadAnimation(this,R.anim.set)
        val Animasi3 = AnimationUtils.loadAnimation(this,R.anim.translate)
        val Animasi2 = AnimationUtils.loadAnimation(this,R.anim.scale)
        val logo = findViewById<View>(R.id.login_gambar) as ImageView
        val edit1 = findViewById<View>(R.id.et_username) as EditText
        val edit2 = findViewById<View>(R.id.et_password) as EditText
        val btn = findViewById<View>(R.id.btn_login) as Button
        val btn1 = findViewById<View>(R.id.btn_daftar) as Button
        logo.startAnimation(Animasi4)
        edit1.startAnimation(Animasi3)
        edit2.startAnimation(Animasi3)
        btn.startAnimation(Animasi2)
        btn1.startAnimation(Animasi2)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        btn4 = findViewById<View>(R.id.lupa) as TextView

        btn_daftar.setOnClickListener {
            val intent  = Intent(this@SignInActivity,
            SignUpActivity::class.java)
            startActivity(intent)
        }

        btn4.setOnClickListener {
            showDialog4()
        }


        preferences.setValues("onboarding", "1")
        if (preferences.getValues("status").equals("1")) {
            finishAffinity()

            val intent = Intent(this@SignInActivity,
                HomeScreenActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan tulis Username Anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan tulis Password Anda"
                et_password.requestFocus()
            } else {

                var statusUsername = iUsername.indexOf(".")
                if (statusUsername >=0) {
                    et_username.error = "Silahkan tulis Username Anda tanpa ."
                    et_username.requestFocus()
                } else {
                    pushLogin(iUsername, iPassword)
                }
            }
        }

        btn_daftar.setOnClickListener {
            val intent = Intent(this@SignInActivity,
                SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()

                } else {
                    if (user.password.equals(iPassword)){
                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG).show()

                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("user", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("saldo", user.saldo.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()


                        val intent = Intent(this@SignInActivity,
                            HomeScreenActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    fun showDialog4(){

        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.lupa_activity)
        myDialog.setTitle("Ok Pesan di proses ")

        image2 = myDialog.findViewById<View>(R.id.close) as ImageView
        image2.isEnabled = true
        image2.setOnClickListener{
            Toast.makeText(applicationContext, "Ok tunggu pesan di proses ",Toast.LENGTH_SHORT).show()
            myDialog.cancel()
        }

        myDialog.show()
    }

}
