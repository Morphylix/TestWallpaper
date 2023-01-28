package com.morphylix.android.testwallpaper.data.local

import com.morphylix.android.testwallpaper.domain.model.domain.Category
import javax.inject.Inject

class Categories @Inject constructor() {
    val categoryList = listOf(
        Category("Fashion"),
        Category("Nature"),
        Category("Science"),
        Category("Religion"),
        Category("Animals"),
        Category("Computer"),
        Category("Food"),
        Category("Music")
    )
}