package com.abrahamlay.domain.usecase.user

import androidx.paging.PagedList
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.domain.usecase.BaseUseCase
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-18.
 */
interface UserUseCase : BaseUseCase {
    /**
     * Get user by query use case
     * */
    fun getUsers(params: Params): Flowable<ResultState<PagedList<SearchResultEntity.ItemsItem>>>

    data class Params(val query: String, val page: Int)
}