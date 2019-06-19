package com.remotearth.fake_coder.chatapp

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    var userId: String? = null,
    var timestamp: Long? = null,
    var isSeen: Boolean? = null
): BaseObservable(), Parcelable {

    var text: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.text)
        }
}