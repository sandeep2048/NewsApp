

package com.sandeep.newsfly.model.responses

import com.google.gson.annotations.SerializedName
import com.sandeep.newsfly.database.entity.RecentArticle

data class AllNewsResponse(
        @SerializedName("articles")
        val recentArticles: MutableList<RecentArticle>,
        val status: String,
        val totalResults: Int
)