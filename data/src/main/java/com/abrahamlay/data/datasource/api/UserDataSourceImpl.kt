package com.abrahamlay.data.datasource.api

import com.abrahamlay.data.api.UserApi
import com.abrahamlay.data.common.applyJobExecutorScheduler
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
            .applyJobExecutorScheduler()
            .map { it.map() }
}