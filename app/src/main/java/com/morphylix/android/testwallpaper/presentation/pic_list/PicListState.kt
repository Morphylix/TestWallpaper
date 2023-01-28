package com.morphylix.android.testwallpaper.presentation.pic_list

import com.morphylix.android.testwallpaper.domain.model.domain.Image

sealed class PicListState {
    object Initialized : PicListState()

    object Loading : PicListState()

    class Success(val imageList: List<Image>): PicListState()

    class Error(val e: Exception): PicListState()
}
