package com.remotearth.fake_coder.chatapp.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    /**
     *  Only log
     *  ERROR
     *  WARN
     *  WTF
     */
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {

            if (priority == Log.ERROR && t != null) {
//                Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority)
//                Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag)
//                Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message)
//                Crashlytics.logException(t)
            }
        }
    }
}