package com.abrahamlay.newsearchgithubuser.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.domain.usecase.user.UserUsecase

class HomeViewModel(private val getUserUsecase: UserUsecase) : ViewModel() {
    private val getListMutable =
        MutableLiveData<ResultState<PagedList<SearchResultEntity.ItemsItem>>>()
    internal val getListData: LiveData<ResultState<PagedList<SearchResultEntity.ItemsItem>>>
        get() = getListMutable

    @SuppressLint("CheckResult")
    fun getList(query: String, startPage: Int) {
        getUserUsecase.getUsers(UserUsecase.Params(query, startPage))
            .subscribe {
                getListMutable.postValue(it)
            }
    }

}