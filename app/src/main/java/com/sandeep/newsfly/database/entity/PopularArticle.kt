

package com.sandeep.newsfly.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandeep.newsfly.model.responses.Source
import java.io.Serializable

/**
 * Model for saving popular articles in room database
 */

@Entity(tableName = "popular_articles")
data class PopularArticle(
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val url: String?,
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val source: Source?,
        val title: String?,
        val updatedAt: Long = System.currentTimeMillis(),
        val urlToImage: String?
) : Serializable