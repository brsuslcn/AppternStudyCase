package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appternstudycase.databinding.ActivityCategorySingleBinding

class CategorySingle : AppCompatActivity() {

    private lateinit var binding: ActivityCategorySingleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}