package com.example.projeto06.views

import android.util.Log
import androidx.lifecycle.*

import com.example.projeto06.data.repository.PersonagenRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class PersonagensViewModel(private val repository: PersonagenRepository) : ViewModel() {

    init {
        if(repository.personagens.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }

    val personagens = repository.personagens

    private val _eventNetworkError = MutableLiveData<String>("")

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshPersonagens()
                _eventNetworkError.value = ""
            }catch (networkError: IOException){
                Log.d("Error", "${networkError.message}")
                _eventNetworkError.value = networkError.message
            }
        }


    }



}

class PersonagenVMFactory(private val repository: PersonagenRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonagensViewModel::class.java))
            return PersonagensViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}