package com.remotearth.fake_coder.chatapp.screens

import android.app.Application
import com.remotearth.fake_coder.chatapp.BuildConfig
import com.remotearth.fake_coder.chatapp.utils.ReleaseTree
import timber.log.Timber

class ChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override
                fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format("Class:%s: Line: %s, Method: %s", super.createStackElementTag(element), element.lineNumber, element.methodName)
                }
            })
        } else {
//            Fabric.with(this, Crashlytics(), Answers())
            Timber.plant(ReleaseTree())
        }
    }
}