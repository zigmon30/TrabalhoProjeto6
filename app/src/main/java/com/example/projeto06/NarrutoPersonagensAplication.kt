package com.example.projeto06

import android.app.Application
import com.example.projeto06.data.local.PersonagensDatabase
import com.example.projeto06.data.repository.PersonagemRepository

class NarrutoPersonagensAplication: Application() {

    private val database: PersonagensDatabase by lazy {
        PersonagensDatabase.getInstance(this)
    }

    val repository: PersonagemRepository by lazy {
        PersonagemRepository(database.personagemDao())
    }
}