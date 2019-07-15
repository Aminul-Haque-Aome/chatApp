package com.remotearth.fake_coder.chatapp.pojos

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.remotearth.fake_coder.chatapp.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String? = null,
    var profileImageUrl: String? = "",
    var token: String? = null
): BaseObservable(), Parcelable {

    var name: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    var email: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    var password: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}