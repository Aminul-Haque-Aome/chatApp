package com.remotearth.fake_coder.chatapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import java.sql.Timestamp

object BindingAdapterUtil {

    @BindingAdapter("profileImage")
    @JvmStatic
    fun profileImageAttach(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_select_image)
            .into(imageView)
    }

    @BindingAdapter("timeStamp")
    @JvmStatic
    fun profileImageAttach(textView: TextView, timestamp: Long) {
        textView.text = DateTimeUtil.getDate(timestamp, Constant.DATE_MONTH_YEAR_HOUR_MINUTE_AM_PM)
    }

    @BindingAdapter("receiverImage")
    @JvmStatic
    fun getReceiverImage(imageView: ImageView, userId: String) {
        FireBaseRealTimeDataBaseServiceImpl().retrieveUser(userId, object: FireBaseRealTimeDataBaseCallback.Retrieve {
            override fun onRetrieveSuccess(user: User) {
                Glide.with(imageView.context)
                    .load(user.profileImageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_select_image)
                    .into(imageView)
            }

            override fun onRetrieveFailed(error: String) {
                imageView.setImageResource(R.drawable.ic_select_image)
            }
        })
    }
}