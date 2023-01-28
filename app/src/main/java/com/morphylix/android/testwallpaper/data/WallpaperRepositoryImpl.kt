package com.morphylix.android.testwallpaper.data

import android.util.Log
import com.morphylix.android.testwallpaper.data.api.PixabayApi
import com.morphylix.android.testwallpaper.data.local.Categories
import com.morphylix.android.testwallpaper.domain.WallpaperRepository
import com.morphylix.android.testwallpaper.domain.model.domain.Category
import com.morphylix.android.testwallpaper.domain.model.domain.Image
import javax.inject.Inject

private const val TAG = "WallpaperRepositoryImpl"

class WallpaperRepositoryImpl @Inject constructor(
    private val pixabayApi: PixabayApi,
    private val categories: Categories
) : WallpaperRepository {

    override fun getCategoryList(): List<Category> {
        return categories.categoryList
    }

    override suspend fun getImageList(category: String): List<Image> {
        Log.i(TAG, "${pixabayApi.getImageList(category)}")
        return pixabayApi.getImageList(category.lowercase()).hits
    }
}