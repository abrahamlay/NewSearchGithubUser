package com.abrahamlay.newsearchgithubuser.di

import com.abrahamlay.newsearchgithubuser.ui.main.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Abraham Lay on 2019-10-30.
 */

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}