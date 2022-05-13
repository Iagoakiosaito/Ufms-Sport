package com.example.controledejogos.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val idTeam: Int = 0,
    val description: String
)

