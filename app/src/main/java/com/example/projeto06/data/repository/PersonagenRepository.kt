package com.example.projeto06.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projeto06.data.domain.Personagens
import com.example.projeto06.data.local.NarutoDao
import com.example.projeto06.data.local.asDomainModel

import com.example.projeto06.data.source.SourceNarutoContainer
import com.example.projeto06.data.source.asLocalModel
import com.example.projeto06.network.OpenPokemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonagenRepository(private val narutoDao: NarutoDao) {

    val personagens: LiveData<List<Personagens>> = Transformations.map(
        narutoDao.getAllPersonagens()
    ) {
        it.asDomainModel()
    }

    suspend fun refreshPersonagens() {
        withContext(Dispatchers.IO) {
            val personagens = OpenPokemonApi.retrofitService.getPokemons()
            val PersonagensContainer = SourceNarutoContainer(personagens)
            narutoDao.insertAllPersonagens(PersonagensContainer.asLocalModel())
        }
    }

}