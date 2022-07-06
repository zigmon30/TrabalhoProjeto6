package com.example.projeto06.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projeto06.data.domain.Personagem

@Entity
data class LocalPersonagem(
    @PrimaryKey
    val _id: String,
    val name: String,
    val picture: String,
    val slug: String,
    val team: String,
)

fun List<LocalPersonagem>.asDomainModel(): List<Personagem> {
    return map {
        Personagem(
            _id = it._id,
            name = it.name,
            picture = it.picture,
            slug = it.slug,
            team =it.team,
        )
    }
}
