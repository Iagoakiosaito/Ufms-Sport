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

    private val _subscriberStateEventData = MutableLiveData<TeamState>()
    val subscriberStateEventData: LiveData<TeamState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun saveTeam(description: String) = viewModelScope.launch(Dispatchers.IO) {
        try {

            val id = repository.addTeam(description)
            if(id > 0){
                _subscriberStateEventData.postValue(TeamState.Inserted)
                _messageEventData.postValue(R.string.team_inserted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_insert)
            Log.e(TAG, "saveTeam: $e", )
        }
    }

    sealed class TeamState{
        object Inserted: TeamState()
    }

    companion object {
        private val TAG = AddTeamViewModel::class.java.simpleName
    }
}