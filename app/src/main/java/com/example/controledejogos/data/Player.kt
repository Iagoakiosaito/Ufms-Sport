package com.example.controledejogos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idTeam: Int,
    val name: String,
    val cpf: String,
    val bornAt: Int,
    )