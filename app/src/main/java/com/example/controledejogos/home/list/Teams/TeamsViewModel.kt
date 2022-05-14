package com.example.controledejogos.home.list.Teams

import androidx.lifecycle.*
import com.example.controledejogos.repository.IGamesRepository

class TeamsViewModel(repository: IGamesRepository): ViewModel() {

    val allTeamsEvent = repository.readAllTeams()

}