package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appternstudycase.databinding.ActivityCategorySingleBinding
import com.example.appternstudycase.ui.adapter.ArtistsAdapter
import com.example.appternstudycase.ui.viewmodel.ActivityCtgSingleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategorySingle : AppCompatActivity() {

    private lateinit var binding: ActivityCategorySingleBinding
    private val viewModel:ActivityCtgSingleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gentreId = intent.getIntExtra("genreId", 0)
        val categoryName = intent.getStringExtra("slctdCategory")

        viewModel.artists(gentreId)

        val artistsAdapter = ArtistsAdapter(applicationContext)

        binding.apply {
            txtCategory.text = categoryName
            recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerView.adapter = artistsAdapter
        }

        viewModel.artistsLiveData.observe(this, Observer { artists ->
            artistsAdapter.updateItems(artists)
        })



    }
}