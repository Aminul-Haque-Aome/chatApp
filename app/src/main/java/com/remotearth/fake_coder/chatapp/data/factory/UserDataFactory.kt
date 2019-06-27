package com.remotearth.fake_coder.chatapp.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.data.source.UserDataSource

class UserDataFactory : DataSource.Factory<String, User>() {

    private var dataSourceLiveData = MutableLiveData<UserDataSource>()

    override fun create(): UserDataSource {
        val dataSource = UserDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}