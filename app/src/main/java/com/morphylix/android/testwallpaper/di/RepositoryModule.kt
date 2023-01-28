package com.morphylix.android.testwallpaper.di

import com.morphylix.android.testwallpaper.data.WallpaperRepositoryImpl
import com.morphylix.android.testwallpaper.data.api.PixabayApi
import com.morphylix.android.testwallpaper.data.local.Categories
import com.morphylix.android.testwallpaper.domain.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(pixabayApi: PixabayApi, categories: Categories): WallpaperRepository {
        return WallpaperRepositoryImpl(pixabayApi, categories)
    }
}