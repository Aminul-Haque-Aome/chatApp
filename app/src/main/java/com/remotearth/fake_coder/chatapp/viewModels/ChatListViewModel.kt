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

    fun checkUserStatus() {
        if (fireBaseAuthService.getFireBaseUser() == null) {
            chatListView.navigateToLogin()
        }
    }

    fun showProfile() {
        chatListView.navigateToUserInfo()
    }

    fun loadAllUsers() {
        showLoader()

        fireBaseRealTimeDataBaseService.retrieveAllUsers(object: FireBaseRealTimeDataBaseCallback.UserListRetrieval {
            override fun onRetrieveSuccess(users: List<User>) {
                hideLoader()
                userList.value = users
            }

            override fun onRetrieveFailed(error: String) {
                hideLoader()
                chatListView.showToast(error)
            }
        })
    }

}
