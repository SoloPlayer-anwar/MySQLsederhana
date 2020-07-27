package com.kaspulanwar.SQLITE.Location

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.kaspulanwar.SQLITE.HomeScreenActivity
import com.kaspulanwar.SQLITE.R
import com.kaspulanwar.SQLITE.sign.signin.User
import com.kaspulanwar.SQLITE.utils.Preferences
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_location_user.*
import kotlinx.android.synthetic.main.user_row_item.view.*

class LocationUserActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var backToast : Toast? = null

    lateinit var preferences: Preferences
    lateinit var mDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_user)

        preferences = Preferences(this)
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("User")

        recyclerview_newmessage.adapter

        fetchUser()

        user_name.text = preferences.getValues("nama")
        email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .into(iv_profile)

        back.setOnClickListener {
            val intent = Intent(this@LocationUserActivity,
            HomeScreenActivity::class.java)
            startActivity(intent)

            finish()
        }

    }

    override fun onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            return
        }

        else {
            backToast = Toast.makeText(baseContext,"Tekan tombol back di atas say ",Toast.LENGTH_LONG)
            backToast!!.show()
        }

        backPressedTime = System.currentTimeMillis()
    }



        private fun fetchUser() {
            val ref = FirebaseDatabase.getInstance().getReference("User")
            ref.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot) {
                    val adapter = GroupAdapter<ViewHolder>()

                    p0.children.forEach {
                        Log.d("NewMessage", it.toString())
                        preferences.setValues("onboarding", "1")
                        val user = it.getValue(User::class.java)
                        if (user != null) {
                            adapter.add(UserItem(user))

                        }

                        else if (preferences.getValues("status").equals("1") ){
                            val intent = Intent(this@LocationUserActivity,
                                HomeScreenActivity::class.java)
                            startActivity(intent)
                        }
                    }


                    recyclerview_newmessage.adapter = adapter
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
        }
    }

    class UserItem(val user: User) : Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.username.text = user.nama
            viewHolder.itemView.gmail.text = user.email
            Glide.with(viewHolder.itemView)
                .load(user.url)
                .into(viewHolder.itemView.photo)
        }

        override fun getLayout(): Int {

            return R.layout.user_row_item
        }



    }







