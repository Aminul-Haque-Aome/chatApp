package com.remotearth.fake_coder.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.contracts.ChatListView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class ChatListViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val chatListView: ChatListView
) : BaseViewModel() {

    var userList: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserId() = fireBaseAuthService.getFireBaseUser()?.uid

    fun checkUserStatus() {
        if (fireBaseAuthService.getFireBaseUser() == null) {
            chatListView.navigateToLogin()
        }
    }

    fun showProfile() {
        chatListView.navigateToUserInfo()
    }

    fun retrieveAllUsers() {
        showLoader()

        if (chatListView.isInternetAvailable()!!) {
            retrieve()
        } else {
            hideLoader()
            chatListView.showToast("Please Connect to Internet")
        }
    }

    private fun retrieve() {
        fireBaseRealTimeDataBaseService.retrieveAllUsers(object: FireBaseRealTimeDataBaseCallback.UserListRetrieval {
            override fun onRetrieveSuccess(users: List<User>) {
                hideLoader()

                val listWithoutCurrentUser: MutableList<User> = ArrayList()
                for (user in users) {
                    if (user.id != fireBaseAuthService.getFireBaseUser()?.uid) {
                        listWithoutCurrentUser.add(user)
                    }
                }

                userList.value = listWithoutCurrentUser
            }

            override fun onRetrieveFailed(error: String) {
                hideLoader()
                chatListView.showToast(error)
            }
        })
    }

}
