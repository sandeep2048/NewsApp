

package com.sandeep.newsfly.database.main

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sandeep.newsfly.database.dao.RecentArticlesRemoteKeyDao
import com.sandeep.newsfly.database.dao.PopularArticleDao
import com.sandeep.newsfly.database.dao.RecentArticleDao
import com.sandeep.newsfly.database.entity.RecentArticlesRemoteKey
import com.sandeep.newsfly.database.entity.PopularArticle
import com.sandeep.newsfly.database.entity.RecentArticle
import com.sandeep.newsfly.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Database(entities = [RecentArticle::class, PopularArticle::class, RecentArticlesRemoteKey::class],  version = 16)
@TypeConverters(RoomConverter::class)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun recentArticleDao(): RecentArticleDao
    abstract fun popularArticleDao(): PopularArticleDao
    abstract fun newsRemoteKeyDao(): RecentArticlesRemoteKeyDao

    class Callback @Inject constructor(@ApplicationScope private val applicationScope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }

}