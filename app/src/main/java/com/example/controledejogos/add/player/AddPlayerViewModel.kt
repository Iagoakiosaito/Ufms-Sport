package com.example.controledejogos.add.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controledejogos.R
import com.example.controledejogos.add.team.AddTeamViewModel
import com.example.controledejogos.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPlayerViewModel(
    private val repository: IGamesRepository
) : ViewModel() {

    private val _playerStateEventData = MutableLiveData<AddPlayerViewModel.PlayerState>()
    val playerStateEventData: LiveData<AddPlayerViewModel.PlayerState>
        get() = _playerStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdatePlayer(idTeam: Int, name: String, cpf: String, bornAt: Int, idPlayer: Int = 0){
        if (idPlayer > 0){
            updatePlayer(idTeam, name,  cpf, bornAt, idPlayer)
        } else {
          insertPlayer(idTeam, name, cpf, bornAt)
        }
    }

    private fun updatePlayer(idTeam: Int, name: String, cpf: String, bornAt: Int, idPlayer: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.updatePlayer(idTeam, name, cpf, bornAt, idPlayer)

            _playerStateEventData.postValue(PlayerState.Updated)
            _messageEventData.postValue(R.string.player_updated_succesfully)
        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_update)
            Log.e(TAG, "updatePlayer: $e", )
        }
    }

    private fun insertPlayer(idTeam: Int, name: String, cpf: String, bornAt: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {

            val id = repository.addPlayer(idTeam, name, cpf, bornAt)
            if (id > 0){
                _playerStateEventData.postValue(AddPlayerViewModel.PlayerState.Inserted)
                _messageEventData.postValue(R.string.player_inserted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_insert)
            Log.e(TAG, "savePlayer: $e", )
        }
    }

    fun removePlayer(idPlayer: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (idPlayer > 0){
                repository.deletePlayer(idPlayer)
                _playerStateEventData.postValue(AddPlayerViewModel.PlayerState.Deleted)
                _messageEventData.postValue(R.string.player_deleted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.value = R.string.error_to_delete
            Log.e(TAG, "removeTeam: $e", )
        }
    }

    sealed class PlayerState{
        object Inserted: PlayerState()
        object Updated : PlayerState()
        object Deleted : PlayerState()
    }

    companion object {
        private val TAG = AddPlayerViewModel::class.java.simpleName
    }

}