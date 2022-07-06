package com.example.projeto06.data.source


import com.example.projeto06.data.domain.Personagem
import com.example.projeto06.data.local.LocalPersonagem

data class SourcePersonagemContainer(
    val sourcePersonagens: List<SourcePersonagem>
)
data class SourcePersonagem(
    val _id: String,
    val name: String,
    val picture: String,
    val slug: String,
    val team: String,
)



fun SourcePersonagemContainer.asDomainModel(): List<Personagem> {
    return sourcePersonagens.map {
        Personagem(
            _id = it._id,
            name = it.name,
            picture = it.picture,
            slug = it.slug,
            team =it.team,

        )
    }
}

fun SourcePersonagemContainer.asLocalModel(): List<LocalPersonagem> {
    return sourcePersonagens.map {
        LocalPersonagem(
            _id = it._id,
            name = it.name,
            picture = it.picture,
            slug = it.slug,
            team =it.team,

            )
    }
}