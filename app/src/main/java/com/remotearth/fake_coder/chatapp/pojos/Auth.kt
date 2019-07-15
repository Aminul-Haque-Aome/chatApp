package com.remotearth.fake_coder.chatapp.pojos

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.remotearth.fake_coder.chatapp.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
class Auth: BaseObservable(), Parcelable {

    var mail: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.mail)
        }

    var pass: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.pass)
        }
}