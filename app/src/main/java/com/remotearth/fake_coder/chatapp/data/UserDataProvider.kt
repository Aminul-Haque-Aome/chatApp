package com.remotearth.fake_coder.chatapp.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.data.factory.UserDataFactory
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService

class UserDataProvider(private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService) {

    companion object {
        private const val PAGE_SIZE = 4
    }

    fun getUsers(): LiveData<PagedList<User>>? {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 3)
            .setPrefetchDistance(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(
            UserDataFactory(fireBaseRealTimeDataBaseService),
            config
        ).setInitialLoadKey("").build()
    }
}