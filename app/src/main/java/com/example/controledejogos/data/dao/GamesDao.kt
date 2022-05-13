package com.example.controledejogos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.data.entity.Team

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeam(team: Team): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player): Long

    @Update
    fun updateTeam(team: Team)

    @Update
    fun updatePlayer(player: Player)

    @Query("DELETE FROM Team WHERE idTeam = :idTeam")
    fun deleteTeam(idTeam: Int)

    @Query("DELETE FROM Player WHERE idPlayer = :idPlayer")
    fun deletePlayer(idPlayer: Int)

    @Query("SELECT * FROM Team ORDER BY idTeam ASC")
    fun readAllTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM Player ORDER BY idPlayer ASC")
    fun readAllPlayers(): LiveData<List<Player>>

    @Query("SELECT p.* FROM Player p JOIN Team t ON p.idTeam = t.idTeam WHERE t.idTeam = :idTeam")
    fun getTeamWithPlayers(idTeam: Int): LiveData<List<Player>>
}