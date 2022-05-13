package com.example.controledejogos.repository

import androidx.lifecycle.LiveData
import com.example.controledejogos.data.dao.GamesDao
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.data.entity.Team

class DatabaseDataSource(
    private val dao: GamesDao
): IGamesRepository {

    override suspend fun addTeam(description: String): Long {
        val team = Team(description = description)
        return dao.addTeam(team)
    }

    override suspend fun addPlayer(idTeam: Int, name: String, cpf: String, bornAt: Int): Long {
        val player = Player(idTeam = idTeam, name = name, cpf = cpf, bornAt = bornAt)

        return dao.addPlayer(player)
    }

    override suspend fun updateTeam(description: String) {
        val team = Team(description = description)
        dao.updateTeam(team)
    }

    override suspend fun updatePlayer(idTeam: Int, name: String, cpf: String, bornAt: Int) {
        val player = Player(idTeam = idTeam, name = name, cpf = cpf, bornAt = bornAt)
        dao.updatePlayer(player)
    }

    override suspend fun deleteTeam(idTeam: Int) {
        dao.deleteTeam(idTeam)
    }

    override suspend fun deletePlayer(idPlayer: Int) {
        dao.deletePlayer(idPlayer)
    }

    override fun readAllTeams(): LiveData<List<Team>> {
        return dao.readAllTeams()
    }

    override fun readAllPlayers(): LiveData<List<Player>> {
        return dao.readAllPlayers()
    }


}