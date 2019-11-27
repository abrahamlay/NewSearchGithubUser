package com.abrahamlay.data.datasource.api

import android.annotation.SuppressLint
import androidx.paging.DataSource
import com.abrahamlay.data.datasource.BaseDataSource
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by Abraham Lay on 2019-11-19.
 */
@SuppressLint("CheckResult")
fun getUsers(
    apiSource: UserDataSource,
    query: String,
    page: Int,
    onResult: (result: ResultState<SearchResultEntity>) -> Unit
) {
    apiSource.searchUser(query ,page)
        .subscribe({ data ->
            onResult(ResultState.Success(data))
        }, { throwable ->
            onResult(ResultState.Error(throwable, null))
        })
}

interface UserDataSource : BaseDataSource {

    /**
     * Get all of users from network
     */
    fun searchUser(query: String, page: Int): Single<SearchResultEntity>
}