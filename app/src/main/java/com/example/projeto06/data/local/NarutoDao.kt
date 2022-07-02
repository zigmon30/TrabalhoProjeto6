package com.example.projeto06.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projeto06.data.domain.Personagens

@Dao
interface NarutoDao {

    @Query("SELECT * FROM Localnaruto order by name")
    fun getAllPersonagens(): LiveData<List<LocalNaruto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPersonagens(personagens:  List<LocalNaruto>)

}





