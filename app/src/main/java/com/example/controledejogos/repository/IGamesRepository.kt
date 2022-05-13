package com.example.controledejogos.repository

import androidx.lifecycle.LiveData
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.data.entity.Team

interface IGamesRepository {

    suspend fun addTeam(description: String): Long

    suspend fun addPlayer(idTeam: Int, name: String, cpf: String, bornAt: Int): Long

    suspend fun updateTeam(description: String)

    suspend fun updatePlayer(idTeam: Int, name: String, cpf: String, bornAt: Int)

    suspend fun deleteTeam(idTeam: Int)

    suspend fun deletePlayer(idPlayer: Int)

    fun readAllTeams(): LiveData<List<Team>>

    fun readAllPlayers(): LiveData<List<Player>>

}