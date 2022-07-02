package com.example.projeto06

import PersonagensDatabase
import android.app.Application
import com.example.projeto06.data.repository.PersonagenRepository

class NarutoPersonagensApplication: Application() {

    private val database: PersonagensDatabase by lazy {
        PersonagensDatabase.getInstance(this)
    }

    val repository: PersonagenRepository by lazy {
        PersonagenRepository(database.personagenDao())
    }
}