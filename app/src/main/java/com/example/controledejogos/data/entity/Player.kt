package com.example.controledejogos.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val idPlayer: Int = 0,
    @ForeignKey(
        entity = Team::class,
        parentColumns = ["idTeam"],
        childColumns = ["idTeam"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val idTeam: Int,
    val name: String,
    val cpf: String,
    val bornAt: Int,
)