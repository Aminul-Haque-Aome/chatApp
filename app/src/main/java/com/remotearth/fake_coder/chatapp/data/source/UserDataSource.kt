package com.remotearth.fake_coder.chatapp.data.source

import androidx.paging.ItemKeyedDataSource
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService

class UserDataSource(
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService
) : ItemKeyedDataSource<String, User>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<User>) {
        fireBaseRealTimeDataBaseService.retrieveAllUsers(
            params.requestedLoadSize,
            object : FireBaseRealTimeDataBaseCallback.UserListRetrieval {
                override fun onRetrieveSuccess(users: List<User>) {

                }

                override fun onRetrieveFailed(error: String) {

                }
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<User>) {
        fireBaseRealTimeDataBaseService.retrieveAllUsersBefore(
            params.key,
            params.requestedLoadSize,
            object : FireBaseRealTimeDataBaseCallback.UserListRetrieval {
                override fun onRetrieveSuccess(users: List<User>) {

                }

                override fun onRetrieveFailed(error: String) {

                }
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<User>) {
        fireBaseRealTimeDataBaseService.retrieveAllUsersAfter(
            params.key,
            params.requestedLoadSize,
            object : FireBaseRealTimeDataBaseCallback.UserListRetrieval {
                override fun onRetrieveSuccess(users: List<User>) {

                }

                override fun onRetrieveFailed(error: String) {

                }
            })
    }

    override fun getKey(item: User) = item.id ?: ""
}