package com.example.projeto06.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto06.data.Ator
import com.example.projeto06.network.HpApi
import kotlinx.coroutines.launch

class AtoresViewModel: ViewModel() {

    private val _atorList = MutableLiveData<List<Ator>>()
    val atorList: LiveData<List<Ator>>
        get() = _atorList

    init {
        getAtores()
    }

    @SuppressLint("NullSafeMutableLiveData")
    private fun getAtores(){
        viewModelScope.launch {
            try {
                val listResult = HpApi.retrofitService.getAtor()
                _atorList.value = listResult
            }catch (e: Exception){
                _atorList.value = null
                Log.d("GetAtores","${e.message}")
            }
        }
    }
}