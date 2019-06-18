package com.remotearth.fake_coder.chatapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.remotearth.fake_coder.chatapp.R
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
}