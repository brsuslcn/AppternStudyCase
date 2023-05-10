package com.example.appternstudycase.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appternstudycase.databinding.FragmentCategoriesBinding
import com.example.appternstudycase.ui.adapter.CategoriesAdapter
import com.example.appternstudycase.ui.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        viewModel.categories()

        val categoriesAdapter = CategoriesAdapter(requireContext())
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = categoriesAdapter
        }

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.updateItems(categories)
        })

        return binding.root
    }
}