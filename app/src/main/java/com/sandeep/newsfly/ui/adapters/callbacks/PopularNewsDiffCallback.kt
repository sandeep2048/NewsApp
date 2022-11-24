

package com.sandeep.newsfly.ui.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.sandeep.newsfly.database.entity.PopularArticle

class PopularNewsDiffCallback : DiffUtil.ItemCallback<PopularArticle>() {

    override fun areItemsTheSame(oldItem: PopularArticle, newItem: PopularArticle): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: PopularArticle, newItem: PopularArticle): Boolean {
        return oldItem == newItem
    }

}