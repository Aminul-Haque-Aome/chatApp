package com.remotearth.fake_coder.chatapp.data.source

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService

class UserDataSource: ItemKeyedDataSource<String, User>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<User>) {
        // ... call fireBase method to load UserList
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<User>) {
        // ... show loader
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<User>) {
        // ... call fireBase method to load UserList
    }

    override fun getKey(item: User) = item.id ?: ""

}