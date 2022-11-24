

package com.sandeep.newsfly.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.sandeep.newsfly.database.entity.PopularArticle
import com.sandeep.newsfly.database.entity.RecentArticle
import com.sandeep.newsfly.database.main.ArticlesDatabase
import com.sandeep.newsfly.model.responses.AllNewsResponse
import com.sandeep.newsfly.network.ApiStatus
import com.sandeep.newsfly.network.BaseDataSource
import com.sandeep.newsfly.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@ExperimentalPagingApi
class AllNewsRepository @Inject constructor(private val apiDataSource: ApiDataSource, private val articlesDatabase: ArticlesDatabase): BaseDataSource()  {

    private val newsArticleDao = articlesDatabase.recentArticleDao()
    private val popularNewsArticleDao = articlesDatabase.popularArticleDao()

    /**
     * Get popular news, without pagination but with offline support
     */

    fun getPopularNews(
        forceRefresh: Boolean,
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<ApiStatus<List<PopularArticle>>> =
        networkBoundResource(
            query = {
               popularNewsArticleDao.getAllPopularArticles()
            },
            fetch = {
                val response =  apiDataSource.getPopularNews()
                response.popularArticles
            },
            saveFetchResult = { popularNews->
                val popularNewsArticles = popularNews.map { it }
                articlesDatabase.withTransaction {
                    popularNewsArticleDao.deletePopularArticles()
                    popularNewsArticleDao.savePopularArticles(popularNewsArticles)
                }
            },
            shouldFetch = { cachedPopularArticles->
                if (forceRefresh){
                    true
                }
                else{
                    val sortedArticles = cachedPopularArticles.sortedBy { article->
                        article.updatedAt
                    }
                    val oldestTimeStamp = sortedArticles.firstOrNull()?.updatedAt
                    val needsRefresh = oldestTimeStamp == null ||
                            oldestTimeStamp < System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(60)
                    needsRefresh
                }
            },
            onFetchSuccess = onFetchSuccess,
            onFetchFailed = { t  ->
                if (t !is HttpException && t !is IOException) {
                    throw t
                }
                onFetchFailed(t)
            }
        )


    /**
     * Get recent paginated news with offline support
     */

    fun getRecentNews() : Flow<PagingData<RecentArticle>> =
        Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false),
                remoteMediator = RecentArticlesRemoteMediator(apiDataSource = apiDataSource, newsArticleDb = articlesDatabase),
                pagingSourceFactory = {
                    newsArticleDao.getAllRecentArticles()
                }
        ).flow



    /**
     * Search for news without pagination and offline support
     */

    suspend fun searchForNewsItem(q: String, page: Int?) : ApiStatus<AllNewsResponse> {
       return safeApiCall {
            apiDataSource.searchForNews(q, page)
        }
    }


}