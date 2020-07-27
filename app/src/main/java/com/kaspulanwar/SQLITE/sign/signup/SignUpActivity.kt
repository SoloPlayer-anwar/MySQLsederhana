package com.kaspulanwar.SQLITE.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signin.SignInActivity
import com.kaspulanwar.SQLITE.sign.signin.User
import com.kaspulanwar.SQLITE.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        back.setOnClickListener {
            val intent = Intent(this@SignUpActivity,
            SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)
        val translate = AnimationUtils.loadAnimation(this,R.anim.translate)
        val daftar_gambar = findViewById<View>(R.id.daftar_gambar) as ImageView
        val et_username = findViewById<View>(R.id.et_username) as EditText
        val et_password = findViewById<View>(R.id.et_password) as EditText
        val et_nama = findViewById<View>(R.id.et_nama) as EditText
        val et_email = findViewById<View>(R.id.et_email) as EditText
        val save_akun = findViewById<View>(R.id.save_akun) as Button
        et_username.startAnimation(translate)
        et_password.startAnimation(translate)
        et_nama.startAnimation(translate)
        et_email.startAnimation(translate)
        save_akun.startAnimation(scale)
        daftar_gambar.startAnimation(scale)


        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        save_akun.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")) {
                et_username.error = "Silahkan isi Username"
                et_username.requestFocus()
            } else if (sPassword.equals("")) {
                et_password.error = "Silahkan isi Password"
                et_password.requestFocus()
            } else if (sNama.equals("")) {
                et_nama.error = "Silahkan isi Nama"
                et_nama.requestFocus()
            } else if (sEmail.equals("")) {
                et_email.error = "Silahkan isi Email"
                et_email.requestFocus()
            } else {

                var statusUsername = sUsername.indexOf(".")
                if (statusUsername >=0) {
                    et_username.error = "Silahkan tulis Username Anda tanpa ."
                    et_username.requestFocus()
                } else {
                    saveUser(sUsername, sPassword, sNama, sEmail)
                }

            }
        }
    }

    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String) {

        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkingUsername(sUsername, user)

        }

    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mFirebaseDatabase.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@SignUpActivity,
                        PhotoScreenActivity::class.java).putExtra("nama", data.nama)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })



    }

}
