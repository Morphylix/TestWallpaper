package com.morphylix.android.testwallpaper.domain

import com.morphylix.android.testwallpaper.domain.model.domain.Category
import com.morphylix.android.testwallpaper.domain.model.domain.Image

interface WallpaperRepository {

    fun getCategoryList(): List<Category>

    suspend fun getImageList(category: String): List<Image>
}