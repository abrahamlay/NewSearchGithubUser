package com.abrahamlay.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.abrahamlay.data.datasource.api.UserDataSource
import com.abrahamlay.data.datasource.api.getUsers
import com.abrahamlay.data.datasource.database.UserDatabaseDataSource
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity

class UserRepoBoundaryCallback(
    private val apiSource: UserDataSource,
    private val query: String,
    private val database: UserDatabaseDataSource
) :
    PagedList.BoundaryCallback<SearchResultEntity.ItemsItem>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1
    private val _networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should send request to the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to send a request to the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: SearchResultEntity.ItemsItem) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getUsers(apiSource, query, lastRequestedPage) { users ->
            when (users) {
                is ResultState.Success -> {
                    database.persist(users.data.items!!) {
                        lastRequestedPage++
                        isRequestInProgress = false
                    }
                }
                is ResultState.Error -> {
                    _networkErrors.postValue(users.throwable.message)
                    isRequestInProgress = false
                }
            }
        }
    }
}
