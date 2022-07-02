package com.example.projeto06.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projeto06.data.domain.Personagens


@Entity
data class LocalNaruto(
    @PrimaryKey
    val _id: String,
    val name: String,
    val picture: String,

)

fun List<LocalNaruto>.asDomainModel(): List<Personagens> {
    return map{
        Personagens(
            _id = it._id,
            name = it.name,
            picture = it.picture
        )
    }
}
