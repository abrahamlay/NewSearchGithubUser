package com.abrahamlay.newsearchgithubuser.di

import com.abrahamlay.data.api.UserApi
import com.abrahamlay.newsearchgithubuser.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abraham Lay on 2019-11-23.
 */

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { okHttp() }
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
}

private fun okHttpBuilder() = OkHttpClient.Builder()

private fun okHttp() = okHttpBuilder().build()