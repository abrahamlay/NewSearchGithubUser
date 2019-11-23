package com.abrahamlay.data.datasource.api

import androidx.paging.DataSource
import com.abrahamlay.data.api.UserApi
import com.abrahamlay.data.common.applyIoScheduler
import com.abrahamlay.data.mapper.map
import com.abrahamlay.domain.entity.SearchResultEntity
import io.reactivex.Single

/**
 * Created by Abraham Lay on 2019-11-19.
 */
class UserDataSourceImpl(private val api: UserApi) :
    UserDataSource {

    override fun searchUser(query: String, page: Int): Single<SearchResultEntity> =
        api.searchUsers(query, page)
            .applyIoScheduler()
            .map { it.map() }
}