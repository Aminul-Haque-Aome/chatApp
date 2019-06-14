package com.remotearth.fake_coder.chatapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String?,
    var name: String?,
    var profileImageUrl: String?,
    var email: String?,
    var password: String?
): Parcelable