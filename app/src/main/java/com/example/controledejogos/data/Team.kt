package com.example.controledejogos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val idTeam: Int,
    val description: String
)
