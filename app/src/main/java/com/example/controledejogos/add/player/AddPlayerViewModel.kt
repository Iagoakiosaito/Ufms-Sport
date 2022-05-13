package com.example.controledejogos.add.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controledejogos.R
import com.example.controledejogos.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPlayerViewModel(
    private val repository: IGamesRepository
) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<AddPlayerViewModel.TeamState>()
    val subscriberStateEventData: LiveData<AddPlayerViewModel.TeamState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun savePlayer(idTeam: Int, name: String, cpf: String, bornAt: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {

            val id = repository.addPlayer(idTeam, name, cpf, bornAt)
            if (id > 0){
                _subscriberStateEventData.postValue(AddPlayerViewModel.TeamState.Inserted)
                _messageEventData.postValue(R.string.player_inserted_successfully)
            }

        } catch (e: Exception){
            _messageEventData.postValue(R.string.error_to_insert)
            Log.e(TAG, "savePlayer: $e", )
        }
    }

    sealed class TeamState{
        object Inserted: TeamState()
    }

    companion object {
        private val TAG = AddPlayerViewModel::class.java.simpleName
    }

}