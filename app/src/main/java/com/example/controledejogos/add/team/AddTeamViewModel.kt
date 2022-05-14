package com.example.controledejogos.add.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controledejogos.R
import com.example.controledejogos.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTeamViewModel(
    private val repository: IGamesRepository
) : ViewModel() {

    private val _teamStateEventData = MutableLiveData<TeamState>()
    val teamStateEventData: LiveData<TeamState>
        get() = _teamStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateTeam(description: String, idTeam: Int){
        if (idTeam > 0){
            updateTeam(description, idTeam)
        } else {
            insertTeam(description)
        }
    }

    private fun updateTeam(description: String, idTeam: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.updateTeam(description, idTeam)

            _teamStateEventData.postValue(TeamState.Updated)
            _messageEventData.postValue(R.string.team_updated_successfully)

        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_update)
            Log.e(TAG, "updateTeam: $e", )
        }
    }

    private fun insertTeam(description: String) = viewModelScope.launch(Dispatchers.IO) {
        try {

            val id = repository.addTeam(description)
            if(id > 0){
                _teamStateEventData.postValue(TeamState.Inserted)
                _messageEventData.postValue(R.string.team_inserted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_insert)
            Log.e(TAG, "saveTeam: $e", )
        }
    }

    fun removeTeam(idTeam: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (idTeam > 0){
                repository.deleteTeam(idTeam)
                _teamStateEventData.postValue(TeamState.Deleted)
                _messageEventData.postValue(R.string.team_deleted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.value = R.string.error_to_delete
            Log.e(TAG, "removeTeam: $e", )
        }
    }

    sealed class TeamState{
        object Inserted: TeamState()
        object Updated : TeamState()
        object Deleted : TeamState()
    }

    companion object {
        private val TAG = AddTeamViewModel::class.java.simpleName
    }
}