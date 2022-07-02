package com.example.projeto06.data.source

import com.example.projeto06.data.domain.Personagens
import com.example.projeto06.data.local.LocalNaruto

data class SourceNarutoContainer(
    val sourceNaruto: List<SourceNaruto>
)

data class SourceNaruto(

    val _id: String,
    val name: String,
    val picture: String,

)


fun SourceNarutoContainer.asDomainModel(): List<Personagens> {
    return sourceNaruto.map{
        Personagens(
            _id = it._id,
            name = it.name,
            picture = it.picture
        )
    }
}

fun SourceNarutoContainer.asLocalModel(): List<LocalNaruto> {
    return sourceNaruto.map{
        LocalNaruto(
            _id = it._id,
            name = it.name,
            picture = it.picture
        )
    }
}