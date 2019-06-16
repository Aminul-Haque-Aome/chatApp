package com.remotearth.fake_coder.chatapp.services.impls

import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.google.firebase.iid.FirebaseInstanceId

class FireBaseTokenServiceImpl: FireBaseTokenService {

    override fun generateToken(): String? {
        var token: String? = null

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    token = task.result!!.token
                }
            }

        return token
    }
}