

package com.sandeep.newsfly.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.sandeep.newsfly.database.entity.RecentArticle

/**
 * Contains data access object (DAO) used for querying recent articles from database
 */

@Dao
interface RecentArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecentArticles(recentArticles: List<RecentArticle>)

    @Query("SELECT * FROM recent_articles")
    fun getAllRecentArticles(): PagingSource<Int, RecentArticle>

    @Query("DELETE FROM recent_articles")
    suspend fun deleteRecentArticles()

}