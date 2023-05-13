package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appternstudycase.R
import com.example.appternstudycase.databinding.ActivityMainBinding
import com.example.appternstudycase.ui.fragment.CategoriesFragment
import com.example.appternstudycase.ui.fragment.LikesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSmoothBottomMenu()


    }

    private fun setupSmoothBottomMenu() {

        binding.bottomBar.onItemSelected = { position ->
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
                0 -> transaction.replace(binding.fragmentContainerView.id, CategoriesFragment())
                1 -> transaction.replace(binding.fragmentContainerView.id, LikesFragment())
            }
            transaction.commit()
        }
    }

}