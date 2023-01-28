package com.morphylix.android.testwallpaper.domain.usecase

import com.morphylix.android.testwallpaper.domain.WallpaperRepository
import com.morphylix.android.testwallpaper.domain.model.domain.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val wallpaperRepository: WallpaperRepository) {
    fun execute(): List<Category> {
        return wallpaperRepository.getCategoryList()
    }
}