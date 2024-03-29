package com.abrahamlay.newsearchgithubuser.di

import com.abrahamlay.data.repository.UserRepositoryImpl
import com.abrahamlay.domain.repository.UserRepository
import com.abrahamlay.domain.usecase.user.UserUseCase
import com.abrahamlay.domain.usecase.user.UserUseCaseImpl
import org.koin.dsl.module

/**
 * Created by Abraham Lay on 2019-10-30.
 */

val useCaseModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(),get()) }
    single<UserUseCase> { UserUseCaseImpl(get()) }
}