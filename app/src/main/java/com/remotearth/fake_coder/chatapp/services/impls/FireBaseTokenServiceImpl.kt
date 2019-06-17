package com.remotearth.fake_coder.chatapp.services.impls

import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.google.firebase.iid.FirebaseInstanceId
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseTokenReceiveCallBack
import timber.log.Timber

class FireBaseTokenServiceImpl: FireBaseTokenService {

    override fun generateToken(fireBaseTokenReceiveCallBack: FireBaseTokenReceiveCallBack) {

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    fireBaseTokenReceiveCallBack.onTokenReceived(task.result!!.token)

                } else {
                    Timber.e(task.exception)
                    fireBaseTokenReceiveCallBack.onTokenReceivedFailed()
                }
            }
    }
}