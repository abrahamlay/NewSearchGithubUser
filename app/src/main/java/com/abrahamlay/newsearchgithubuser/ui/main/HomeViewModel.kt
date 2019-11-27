package com.abrahamlay.newsearchgithubuser.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.domain.usecase.user.UserUseCase
import com.abrahamlay.newsearchgithubuser.ui.base.BaseViewModel

class HomeViewModel(private val getUserUseCase: UserUseCase) : BaseViewModel() {
    private val getListMutable =
        MutableLiveData<ResultState<PagedList<SearchResultEntity.ItemsItem>>>()
    internal val getListData: LiveData<ResultState<PagedList<SearchResultEntity.ItemsItem>>>
        get() = getListMutable

    @SuppressLint("CheckResult")
    fun getList(query: String, startPage: Int) {
        getUserUseCase.getUsers(UserUseCase.Params(query, startPage))
            .subscribe {
                getListMutable.postValue(it)
            }
    }

}