package com.morphylix.android.testwallpaper.presentation.category_choice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.morphylix.android.testwallpaper.databinding.FragmentCategoryChoiceBinding
import com.morphylix.android.testwallpaper.presentation.BaseFragment

class CategoryChoiceFragment : BaseFragment<FragmentCategoryChoiceBinding>({ inflater, container ->
    FragmentCategoryChoiceBinding.inflate(inflater, container, false)
}) {

    private val categoryChoiceViewModel: CategoryChoiceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryList = categoryChoiceViewModel.getCategories()
        with(binding) {
            categoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            categoriesRecyclerView.adapter = CategoryChoiceAdapter(categoryList)
        }
    }

}