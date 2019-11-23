package com.abrahamlay.data.datasource.database

import androidx.paging.DataSource
import com.abrahamlay.data.datasource.BaseDataSource
import com.abrahamlay.domain.entity.SearchResultEntity

/**
 * Created by Abraham Lay on 2019-11-22.
 */

interface UserDatabaseDataSource : BaseDataSource {
    /**
     * Get all of users from database implementation
     */
    fun getUsers(query: String): DataSource.Factory<Int, SearchResultEntity.ItemsItem>

    /**
     * Persist all of users in local database
     */
    fun persist(users: List<SearchResultEntity.ItemsItem>, insertFinished: () -> Unit)
}