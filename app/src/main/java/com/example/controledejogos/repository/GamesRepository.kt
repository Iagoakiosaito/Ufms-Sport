package com.example.controledejogos.repository

import androidx.lifecycle.LiveData
import com.example.controledejogos.data.GamesDao
import com.example.controledejogos.data.Player
import com.example.controledejogos.data.Team

class GamesRepository(private val gamesDao: GamesDao) {

    val readAllTeams: LiveData<List<Team>> = gamesDao.readAllTeams()
    val readAllPlayers: LiveData<List<Player>> = gamesDao.readAllPlayers()

    suspend fun addTeam(team: Team){
        gamesDao.addTeam(team)
    }

    suspend fun addPlayer(player: Player){
        gamesDao.addPlayer(player)
    }

    suspend fun getTeamWithPlayers(idTeam: Int){
        gamesDao.getTeamWithPlayers(idTeam)
    }
}