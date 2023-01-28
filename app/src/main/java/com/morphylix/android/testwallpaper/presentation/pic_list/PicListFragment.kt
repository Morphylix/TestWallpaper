package com.morphylix.android.testwallpaper.presentation.pic_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.morphylix.android.testwallpaper.databinding.FragmentPicListBinding
import com.morphylix.android.testwallpaper.presentation.BaseFragment
import com.morphylix.android.testwallpaper.util.ErrorHandler
import com.morphylix.android.testwallpaper.util.animateAppear

private const val TAG = "PicListFragment"

class PicListFragment : BaseFragment<FragmentPicListBinding>({ inflater, container ->
    FragmentPicListBinding.inflate(inflater, container, false)
}) {

    private val picListViewModel: PicListViewModel by activityViewModels()
    private val args: PicListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chosenCategory = args.category
        picListViewModel.getImageList(chosenCategory)

        lifecycleScope.launchWhenCreated {
            picListViewModel.state.collect { state ->
                when(state) {
                    is PicListState.Initialized -> {
                    }

                    is PicListState.Loading -> {
                        binding.picListProgressBar.visibility = View.VISIBLE
                    }
                    is PicListState.Success -> {
                        binding.picListProgressBar.visibility = View.GONE
                        Log.i(TAG, "list size is ${state.imageList[0].id}")
                        binding.picRecyclerView.adapter = PicListAdapter(state.imageList)
                        picListViewModel.setInitializedState()
                    }
                    is PicListState.Error -> {
                        with(binding) {
                            picListProgressBar.visibility = View.GONE
                            ErrorHandler.handleException(state.e, requireContext())
                            errorImageView.visibility = View.VISIBLE
                            animateAppear(errorImageView)
                        }
                    }
                }
            }
        }

        with(binding) {
            picRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        }
    }
}