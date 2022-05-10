package com.example.controledejogos.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.controledejogos.data.relation.TeamWithPlayers

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayer(player: Player)

    @Query("SELECT * FROM Team ORDER BY idTeam ASC")
    fun readAllTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM Player ORDER BY id ASC")
    fun readAllPlayers(): LiveData<List<Player>>

    @Transaction
    @Query("SELECT * FROM Team WHERE idTeam = :idTeam")
    suspend fun getTeamWithPlayers(idTeam: Int): List<TeamWithPlayers>
}