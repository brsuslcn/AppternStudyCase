package com.example.appternstudycase.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appternstudycase.R
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.databinding.FragmentLikesBinding
import com.example.appternstudycase.ui.adapter.LikeListener
import com.example.appternstudycase.ui.adapter.LikesAdapter
import com.example.appternstudycase.ui.viewmodel.LikesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LikesFragment : Fragment(), LikeListener {
    private lateinit var binding: FragmentLikesBinding
    private val viewModel : LikesViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {

        viewModel.dbLikes()
        binding = FragmentLikesBinding.inflate(layoutInflater)

        val likesAdapter = LikesAdapter(this,this)
        binding.apply {
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
            recyclerView.adapter=likesAdapter
        }

        viewModel.likedData.observe(viewLifecycleOwner, Observer {likedData ->
            likesAdapter.updateItems(likedData)
        })


        return binding.root
    }

    override fun likeTrack(track: TracksLikesModel) {
       viewModel.likeTrack(track)
    }

    override fun dislikeTrack(track: TracksLikesModel) {
        viewModel.dislikeTrack(track)
    }


    override fun getLikes(): MutableLiveData<List<TracksLikesModel>> {
        return viewModel.likedData
    }
}