package com.morphylix.android.testwallpaper.presentation.category_choice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.morphylix.android.testwallpaper.databinding.CategoryItemBinding
import com.morphylix.android.testwallpaper.domain.model.domain.Category

private const val TAG = "CategoryChoiceAdapter"

class CategoryChoiceAdapter(private val categoryList: List<Category>) :
    Adapter<CategoryChoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChoiceViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryChoiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryChoiceViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}

class CategoryChoiceViewHolder(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var category: Category

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(category: Category) {
        this.category = category
        binding.categoryNameTextView.text = this.category.name
    }

    override fun onClick(v: View) {
        val category = category.name
        val action =
            CategoryChoiceFragmentDirections.actionCategoryChoiceFragmentToPicListFragment(category)
        Log.i(TAG, "Clicked on $category")
        v.findNavController().navigate(action)
    }

}