package com.example.controledejogos.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.controledejogos.data.Player
import com.example.controledejogos.data.Team

data class TeamWithPlayers (

    @Embedded val team: Team,
    @Relation(
        parentColumn = "idTeam",
        entityColumn = "idTeam"
    )
    val players: List<Player>

)
