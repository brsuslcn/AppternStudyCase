package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appternstudycase.databinding.ActivityArtistSingleBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistSingle : AppCompatActivity() {
    private lateinit var binding: ActivityArtistSingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArtistSingleBinding.inflate(layoutInflater)

        val artistName = intent.getStringExtra("artistName")
        val artistPic = intent.getStringExtra("artistPicture")

        binding.apply {
            txtartistName.text = artistName
            Picasso.get()
                .load(artistPic)
                .into(imgArtistPic)

        }




        setContentView(binding.root)
    }
}