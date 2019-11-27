package com.abrahamlay.domain.usecase.user

import androidx.paging.PagedList
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.domain.repository.UserRepository
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-22.
 */

class UserUseCaseImpl(
    private val repository: UserRepository
) : UserUseCase {
    override fun getUsers(params: UserUseCase.Params): Flowable<ResultState<PagedList<SearchResultEntity.ItemsItem>>> =
        repository.searchUsers(params.query, params.page)

}