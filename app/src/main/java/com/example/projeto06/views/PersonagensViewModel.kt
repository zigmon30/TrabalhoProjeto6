package com.example.projeto06.views

import android.util.Log
import androidx.lifecycle.*
import com.example.projeto06.data.repository.PersonagemRepository
import kotlinx.coroutines.launch
import okio.IOException

class PersonagensViewModel( private val repository: PersonagemRepository) : ViewModel() {

    init {
        if (repository.personagens.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }
    val personagens = repository.personagens

    private val _eventoNetworkError = MutableLiveData<String>("")

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshPersonagens()
                _eventoNetworkError.value = ""
            }catch (networkError: IOException){
                Log.d("Error", "{$networkError.message}")
                _eventoNetworkError.value = networkError.message
            }
        }
    }


}

class PersonagemFactory(private val repository: PersonagemRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonagensViewModel::class.java))
            return PersonagensViewModel(repository) as T
        throw IllegalArgumentException("Erro viw model class")

    }

}