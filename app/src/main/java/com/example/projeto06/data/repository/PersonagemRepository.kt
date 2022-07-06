package com.example.projeto06.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projeto06.data.domain.Personagem
import com.example.projeto06.data.local.PersonagemDao
import com.example.projeto06.data.local.asDomainModel
import com.example.projeto06.data.source.NarutoApi
import com.example.projeto06.data.source.SourcePersonagemContainer
import com.example.projeto06.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonagemRepository(private val personagemDao: PersonagemDao) {

    val personagens: LiveData<List<Personagem>> = Transformations.map(
        personagemDao.getAllPersonagens()
    ){
        it.asDomainModel()
    }

    suspend fun refreshPersonagens(){
        withContext(Dispatchers.IO){
            val personagens = NarutoApi.retrofitService.getPersonagens()
            val personagemContainer = SourcePersonagemContainer(personagens)
            personagemDao.insertAllPersonagens(personagemContainer.asLocalModel())
        }
    }
}