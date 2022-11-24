

package com.sandeep.newsfly.data

import com.sandeep.newsfly.network.ApiService
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    //get all news
    suspend fun getRecentNews(page: Int?, pageSize: Int) = apiService.getRecentNews(page = page, pageSize = pageSize)

    //get popular news
    suspend fun getPopularNews() = apiService.getPopularNews()

    //search for news
    suspend fun searchForNews(q: String, page: Int?) = apiService.searchForNews(q, page)

}