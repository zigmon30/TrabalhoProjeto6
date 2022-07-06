package com.example.projeto06.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto06.data.Personagem
import com.example.projeto06.network.NarutoApi
import kotlinx.coroutines.launch

class PersonagensViewModel : ViewModel() {

    private val _personagemList = MutableLiveData<List<Personagem>>()
    val personagemList: LiveData<List<Personagem>>
        get() = _personagemList

    init {
        getPersonagens()
    }

    private fun getPersonagens(){
        viewModelScope.launch {
            try {
                val listResult = NarutoApi.retrofitService.getPersonagens()
                _personagemList.value = listResult
            }catch (e: Exception){
                _personagemList.value = null
                Log.d("GetErro personagens","${e.message}")
            }
        }
    }
}