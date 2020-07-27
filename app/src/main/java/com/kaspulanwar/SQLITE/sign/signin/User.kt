package com.kaspulanwar.SQLITE.sign.signin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class User (): Parcelable{

    var email: String ?=""
    var nama: String ?=""
    var password: String ?=""
    var url: String ?=""
    var username: String ?=""
    var saldo: String ?=""
    var status: String ? = ""

}