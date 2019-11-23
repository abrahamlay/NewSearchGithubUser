package com.abrahamlay.data.api

import com.abrahamlay.data.dto.SearchResultDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abraham Lay on 2019-11-18.
 */
interface UserApi {
    @GET("search/users?")
    fun searchUsers(@Query("q") searchQuery: String, @Query("page") page: Int): Single<SearchResultDto>
}