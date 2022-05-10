package com.example.controledejogos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.controledejogos.data.GamesDatabase
import com.example.controledejogos.data.Player
import com.example.controledejogos.data.Team
import com.example.controledejogos.repository.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamesViewModel(application: Application): AndroidViewModel(application) {

    private val readAllTeams: LiveData<List<Team>>
    private val readAllPlayers: LiveData<List<Player>>
    private val repository: GamesRepository

    init {
        val gamesDao = GamesDatabase.getDatabase(application).gamesDao()
        repository = GamesRepository(gamesDao)
        readAllTeams = repository.readAllTeams
        readAllPlayers = repository.readAllPlayers
    }

    fun addTeam(team: Team){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTeam(team)
        }
    }
}