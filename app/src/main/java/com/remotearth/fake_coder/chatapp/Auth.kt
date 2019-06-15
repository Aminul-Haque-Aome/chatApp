package com.remotearth.fake_coder.chatapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Auth(
    var email: String?,
    var password: String?
): Parcelable