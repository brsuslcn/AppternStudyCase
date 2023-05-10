package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appternstudycase.databinding.ActivityArtistSingleBinding
import com.example.appternstudycase.ui.adapter.ArtistSingleAdapter
import com.example.appternstudycase.ui.viewmodel.ArtistSingleViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistSingle : AppCompatActivity() {
    private lateinit var binding: ActivityArtistSingleBinding
    private val viewModel : ArtistSingleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArtistSingleBinding.inflate(layoutInflater)

        val artistName = intent.getStringExtra("artistName")
        val artistPic = intent.getStringExtra("artistPicture")
        val artistId = intent.getIntExtra("artistId", 0)

        viewModel.albums(artistId)
        val artistSingleAdapter = ArtistSingleAdapter(applicationContext)

        binding.apply {
            txtartistName.text = artistName
            Picasso.get()
                .load(artistPic)
                .into(imgArtistPic)
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = artistSingleAdapter
        }


        viewModel.albumsLiveData.observe(this, Observer {albums ->
            artistSingleAdapter.updateItems(albums)
        })




        setContentView(binding.root)
    }
}