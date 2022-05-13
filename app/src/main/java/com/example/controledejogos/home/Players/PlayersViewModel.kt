package com.example.controledejogos.home.Players

import androidx.lifecycle.ViewModel
import com.example.controledejogos.repository.IGamesRepository

class PlayersViewModel(repository: IGamesRepository): ViewModel() {

    val allPlayersEvent = repository.readAllPlayers()

}