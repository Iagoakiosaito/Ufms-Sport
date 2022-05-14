package com.example.controledejogos.home.list.Players

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.controledejogos.repository.IGamesRepository

class PlayersViewModel(repository: IGamesRepository): ViewModel() {

    val searchQuery = MutableLiveData("")

    private val task = searchQuery.switchMap {
        repository.getTeamWithPlayers(it)
    }

    val allPlayersEvent = task

}