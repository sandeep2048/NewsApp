

package com.sandeep.newsfly.ui.adapters.all

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sandeep.newsfly.database.entity.PopularArticle
import com.sandeep.newsfly.database.entity.RecentArticle


@BindingAdapter("displayRecentImage")
fun ImageView.setDisplayImage(item: RecentArticle){
    Glide.with(this)
        .load(item.urlToImage)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)

}

@BindingAdapter("displayPopularImage")
fun ImageView.setDisplayImage2(item: PopularArticle){
    Glide.with(this)
            .load(item.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}
