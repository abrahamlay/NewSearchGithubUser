package com.abrahamlay.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.abrahamlay.data.common.applyJobExecutorScheduler
import com.abrahamlay.data.datasource.api.UserDataSource
import com.abrahamlay.data.datasource.database.UserDatabaseDataSource
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.domain.repository.UserRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-20.
 */
class UserRepositoryImpl(
    private val apiSource: UserDataSource,
    private val databaseDataSource: UserDatabaseDataSource
) : UserRepository {
    override fun searchUsers(
        query: String,
        page: Int
    ): Flowable<ResultState<PagedList<SearchResultEntity.ItemsItem>>> {

        val dataSourceFactory = databaseDataSource.getUsers(query)
        val boundaryCallback = UserRepoBoundaryCallback(apiSource, query, databaseDataSource)
        val data = RxPagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .buildFlowable(BackpressureStrategy.BUFFER)
        return data.applyJobExecutorScheduler().map { d ->
            if (d.size > 0)
                ResultState.Success(d) as ResultState<PagedList<SearchResultEntity.ItemsItem>>
            else
                ResultState.Loading(d) as ResultState<PagedList<SearchResultEntity.ItemsItem>>
        }
            .onErrorReturn { e -> ResultState.Error(e, null) }
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 10
    }
}