package com.remotearth.fake_coder.chatapp.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.data.factory.UserDataFactory

class UserDataProvider {

    companion object {
        private const val PAGE_SIZE = 4
    }

    private var animalDataFactory: UserDataFactory =
        UserDataFactory()

    fun getUsers(): LiveData<PagedList<User>>? {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 3)
            .setPrefetchDistance(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(
            animalDataFactory,
            config
        ).setInitialLoadKey("").build()
    }
}