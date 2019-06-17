package com.remotearth.fake_coder.chatapp.utils

object StringUtil {

    fun isNullOrEmpty(vararg fields: String?): Boolean {
        for (field in fields) {
            if (field == null || field.isEmpty()) {
                return true
            }
        }
        return false
    }

}