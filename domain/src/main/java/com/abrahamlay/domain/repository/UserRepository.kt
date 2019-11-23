package com.abrahamlay.domain.repository

import androidx.paging.PagedList
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-18.
 */

interface UserRepository : BaseRepository {
    fun searchUsers(
        query: String,
        page: Int
    ): Flowable<ResultState<PagedList<SearchResultEntity.ItemsItem>>>
}