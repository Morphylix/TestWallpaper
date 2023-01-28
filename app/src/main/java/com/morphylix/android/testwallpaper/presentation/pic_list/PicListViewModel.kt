package com.morphylix.android.testwallpaper.presentation.pic_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morphylix.android.testwallpaper.domain.usecase.GetImageListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicListViewModel @Inject constructor(private val getImageListUseCase: GetImageListUseCase): ViewModel() {

    private val _state = MutableStateFlow<PicListState>(PicListState.Loading)
    val state: StateFlow<PicListState>
        get() = _state

    fun getImageList(category: String) {
        _state.value = PicListState.Loading
        viewModelScope.launch {
            try {
                _state.value = PicListState.Success(getImageListUseCase.execute(category))
            } catch (e: Exception) {
                _state.value = PicListState.Error(e)
            }
        }
    }

    fun setInitializedState() {
        _state.value = PicListState.Initialized
    }
}