package com.remotearth.fake_coder.chatapp.viewModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    var userId: String? = null,
    var message: String? = null,
    var timestamp: Long? = null,
    var isSeen: Boolean? = null
): Parcelable