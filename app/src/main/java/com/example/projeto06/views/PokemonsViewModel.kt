package com.example.projeto06.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto06.data.Personagens
import com.example.projeto06.network.OpenPokemonApi
import kotlinx.coroutines.launch

class PokemonsViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Personagens>>()
    val pokemonList: LiveData<List<Personagens>>
        get() = _pokemonList

    init {
        getPokemons()
    }

    private fun getPokemons(){
        viewModelScope.launch {
            try {
                val listResult = OpenPokemonApi.retrofitService.getPokemons()
                _pokemonList.value = listResult
            }catch (e: Exception){
                _pokemonList.value = null
                Log.d("Error","${e.message}")
            }
        }
    }
}