package com.remotearth.fake_coder.chatapp.utils

import com.remotearth.fake_coder.chatapp.utils.config.Constant
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    fun getTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    fun getDate(datetime: Long): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = datetime
        val dateFormat = SimpleDateFormat(Constant.DATE_FORMAT_OF_DEFAULT, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getDate(datetime: Long, format: String): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = datetime
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getCalendarFromDate(format: String, date: String): Calendar {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val calendar = Calendar.getInstance()

        try {
            val dateObj = dateFormat.parse(date)
            calendar.time = dateObj
        } catch (e: ParseException) {
            Timber.d(e.message)
        }

        return calendar
    }

    fun getTimeInMillisFromDate(date: String, format: String): Long {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val calendar = Calendar.getInstance()

        try {
            val dateObj = dateFormat.parse(date)
            calendar.time = dateObj
        } catch (e: ParseException) {
            Timber.d(e.message)
        }

        return calendar.timeInMillis
    }

}