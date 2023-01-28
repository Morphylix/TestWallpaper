package com.morphylix.android.testwallpaper.domain.usecase

import com.morphylix.android.testwallpaper.data.WallpaperRepositoryImpl
import com.morphylix.android.testwallpaper.domain.model.domain.Image
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(private val wallpaperRepositoryImpl: WallpaperRepositoryImpl) {

    suspend fun execute(category: String): List<Image> {
        return wallpaperRepositoryImpl.getImageList(category)
    }

}