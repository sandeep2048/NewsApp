

package com.sandeep.newsfly.ui.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.sandeep.newsfly.database.entity.RecentArticle

class RecentNewsDiffCallback : DiffUtil.ItemCallback<RecentArticle>() {

    override fun areItemsTheSame(oldItem: RecentArticle, newItem: RecentArticle): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: RecentArticle, newItem: RecentArticle): Boolean {
        return oldItem == newItem
    }

}