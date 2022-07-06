package com.example.projeto06.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalPersonagem::class], version = 1)
abstract class PersonagensDatabase : RoomDatabase() {

    abstract fun personagemDao(): PersonagemDao

    companion object {

        @Volatile
        private var INSTANCE: PersonagensDatabase? = null

        fun getInstance(context: Context): PersonagensDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagensDatabase::class.java,
                    "personagens_database"

                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}