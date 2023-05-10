package com.example.appternstudycase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appternstudycase.data.model.categories_model.CategoriesModel
import com.example.appternstudycase.data.model.categories_model.Data
import com.example.appternstudycase.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _categoriesLiveData = MutableLiveData<List<Data>>()
    val categoriesLiveData: LiveData<List<Data>> get() = _categoriesLiveData

    fun categories() {
        viewModelScope.launch {
            val response = apiRepository.categories()
            val categoriesItems: CategoriesModel? = response.body()
            _categoriesLiveData.value = categoriesItems?.data
        }
    }
}
