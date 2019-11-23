package com.abrahamlay.newsearchgithubuser.di

import androidx.room.Room
import com.abrahamlay.data.datasource.api.UserDataSource
import com.abrahamlay.data.datasource.api.UserDataSourceImpl
import com.abrahamlay.data.datasource.database.UserDatabaseDataSource
import com.abrahamlay.data.datasource.database.UserDatabaseDataSourceImpl
import com.abrahamlay.data.db.MyDatabase
import com.abrahamlay.data.db.user.UserDao
import org.koin.dsl.module
import java.util.concurrent.Executors

/**
 * Created by Abraham Lay on 2019-10-30.
 */

val dataModule = module {
    single<UserDatabaseDataSource> { UserDatabaseDataSourceImpl(get(), Executors.newSingleThreadExecutor()) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<UserDao> { Room.databaseBuilder(get(), MyDatabase::class.java, "mydatabase")
        .build().userDao() }
}
