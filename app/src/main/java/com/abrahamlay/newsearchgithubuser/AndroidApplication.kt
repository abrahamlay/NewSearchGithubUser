package com.abrahamlay.newsearchgithubuser

import android.app.Application
import com.abrahamlay.newsearchgithubuser.di.apiModule
import com.abrahamlay.newsearchgithubuser.di.dataModule
import com.abrahamlay.newsearchgithubuser.di.useCaseModule
import com.abrahamlay.newsearchgithubuser.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Abraham Lay on 2019-10-30.
 */
class AndroidApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(
                listOf(
                    dataModule,
                    useCaseModule,
                    apiModule,
                    viewModelModule
                )
            )
        }
    }
}