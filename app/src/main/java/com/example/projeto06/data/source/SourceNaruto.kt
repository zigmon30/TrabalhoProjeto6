package com.example.projeto06.data.source

data class SourceNarutoContainer(
    val sourceNaruto: List<SourceNaruto>
)

data class SourceNaruto(

    val _id: String,
    val name: String,
    val picture: String,
    val slug: String,
    val team: String,
)


