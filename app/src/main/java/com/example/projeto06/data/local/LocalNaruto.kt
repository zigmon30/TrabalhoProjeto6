package com.example.projeto06.data.local

import androidx.room.Entity


@Entity
data class LocalNaruto(
    val _id: String,
    val name: String,
    val picture: String,
    val slug: String,
    val team: String,
)
