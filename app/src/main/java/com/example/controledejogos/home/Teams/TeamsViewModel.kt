package com.example.controledejogos.home.Teams

import android.app.Application
import androidx.lifecycle.*
import com.example.controledejogos.data.database.GamesDatabase
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.data.entity.Team
import com.example.controledejogos.repository.DatabaseDataSource
import com.example.controledejogos.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamsViewModel(repository: IGamesRepository): ViewModel() {

    val allTeamsEvent = repository.readAllTeams()

}