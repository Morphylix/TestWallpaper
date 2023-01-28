package com.morphylix.android.testwallpaper.presentation.category_choice

import androidx.lifecycle.ViewModel
import com.morphylix.android.testwallpaper.domain.model.domain.Category
import com.morphylix.android.testwallpaper.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryChoiceViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) :
    ViewModel() {

    fun getCategories(): List<Category> {
        return getCategoriesUseCase.execute()
    }
}