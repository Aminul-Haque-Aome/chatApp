package com.remotearth.fake_coder.chatapp

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String? = null,
    var name: String? = null,
    var profileImageUrl: String? = null,
    var email: String? = null,
    var password: String? = null
): BaseObservable(), Parcelable {

    var observableName: String?
        @Bindable get() = name
        set(value) {
            observableName = value
            notifyPropertyChanged(BR.observableName)
        }

    var observableEmail: String?
        @Bindable get() = email
        set(value) {
            observableEmail = value
            notifyPropertyChanged(BR.observableEmail)
        }

    var observablePassword: String?
        @Bindable get() = password
        set(value) {
            observablePassword = value
            notifyPropertyChanged(BR.observablePassword)
        }
}