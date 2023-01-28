package com.morphylix.android.testwallpaper.presentation.pic_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.morphylix.android.testwallpaper.R
import com.morphylix.android.testwallpaper.databinding.PicItemBinding
import com.morphylix.android.testwallpaper.domain.model.domain.Image
import com.squareup.picasso.Picasso

private const val TAG = "PicListAdapter"

class PicListAdapter(private val imgList: List<Image>) : RecyclerView.Adapter<PicListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListViewHolder {
        val binding = PicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicListViewHolder, position: Int) {
        holder.bind(imgList[position])
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

}

class PicListViewHolder(private val binding: PicItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    private lateinit var image: Image

    fun bind(image: Image) {
        this.image = image
        Log.i(TAG, "Loading image...")
        with(binding) {
            Picasso.get()
                .load(image.previewURL)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(pictureImageView)
        }
    }

    override fun onClick(v: View) {
        val url = image.largeImageURL
        val action = PicListFragmentDirections.actionPicListFragmentToPicDetailsFragment(url)
        v.findNavController().navigate(action)
    }

}