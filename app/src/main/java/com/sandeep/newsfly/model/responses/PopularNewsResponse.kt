

package com.sandeep.newsfly.model.responses

import com.google.gson.annotations.SerializedName
import com.sandeep.newsfly.database.entity.PopularArticle

data class PopularNewsResponse(
        @SerializedName("articles")
        val popularArticles: List<PopularArticle>,
        val status: String,
        val totalResults: Int
)