

package com.sandeep.newsfly.network

import com.sandeep.newsfly.model.responses.AllNewsResponse
import com.sandeep.newsfly.model.responses.PopularNewsResponse
import com.sandeep.newsfly.utils.Urls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //get all recent news in the us
    @GET("everything?q=phone")
    suspend fun getRecentNews(
            @Query("apiKey") apiKey: String = Urls.API_KEY,
            @Query("page") page: Int?,
            @Query("pageSize") pageSize: Int
    ) : AllNewsResponse

    //get all popular news related to apple
    @GET("top-headlines")
    suspend fun getPopularNews(
            @Query("country") country: String = "us",
            @Query("apiKey") apiKey: String = Urls.API_KEY,
            @Query("pageSize") pageSize: Int = 5
    ) : PopularNewsResponse

    //get all news when searching
    @GET("everything")
    suspend fun searchForNews(
            @Query("q") q: String,
            @Query("page") page: Int?,
            @Query("apiKey") apiKey: String = Urls.API_KEY
    ): Response<AllNewsResponse>

}