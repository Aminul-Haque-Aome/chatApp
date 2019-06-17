package com.remotearth.fake_coder.chatapp.utils

import android.app.ProgressDialog
import android.content.Context

class ProgressUtil(context: Context) {
    private var dialog: ProgressDialog = ProgressDialog(context)

    fun showProgressDialog(message: String) {
        dialog.setCancelable(false)
        dialog.setTitle(message)
        dialog.setMessage("Please wait a little bit")
        dialog.show()
    }

    fun hideProgressDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

}