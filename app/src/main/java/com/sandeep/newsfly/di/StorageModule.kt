

package com.sandeep.newsfly.di

import com.sandeep.newsfly.storage.DataStorage
import com.sandeep.newsfly.storage.DataStorageImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindDataStorage(dataStorageImplementation: DataStorageImplementation): DataStorage

}