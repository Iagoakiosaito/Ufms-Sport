package com.example.controledejogos.add.player

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.controledejogos.R
import com.example.controledejogos.add.team.AddTeamViewModel
import com.example.controledejogos.data.dao.GamesDao
import com.example.controledejogos.data.database.GamesDatabase
import com.example.controledejogos.extensions.hideKeyboard
import com.example.controledejogos.repository.DatabaseDataSource
import com.example.controledejogos.repository.IGamesRepository
import kotlinx.android.synthetic.main.add_player_fragment.*

class AddPlayerFragment : Fragment() {

    private lateinit var v : View

    private val viewModel: AddPlayerViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val dao: GamesDao = GamesDatabase.getDatabase(requireContext()).gamesDao()

                val repository: IGamesRepository = DatabaseDataSource(dao)

                return AddPlayerViewModel(repository) as T
            }
        }
    }

    private val args: AddPlayerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.add_player_fragment, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.player?.let { player ->  
            edtName.setText(player.name)
            edtCPF.setText(player.cpf)
            edtBornAt.setText(player.bornAt.toString())
            edtIdTeam.setText(player.idTeam.toString())
            btnSavePlayer.text = getString(R.string.player_button_update)

            btn_delete_player.visibility = View.VISIBLE
        }
        
        createObservers()
        setListeners()
    }

    private fun createObservers() {
        viewModel.playerStateEventData.observe(viewLifecycleOwner) { teamState ->
            when(teamState) {
                is AddPlayerViewModel.PlayerState.Inserted,
                is AddPlayerViewModel.PlayerState.Updated,
                is AddPlayerViewModel.PlayerState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Toast.makeText(requireContext(), stringResId, Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        edtName.text?.clear()
        edtCPF.text?.clear()
        edtBornAt.text?.clear()
        edtIdTeam.text?.clear()

    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        btnSavePlayer.setOnClickListener{
            val name = edtName.text.toString()
            val cpf = edtCPF.text.toString()
            val bornAt = edtBornAt.text.toString().toInt()
            val teamId = edtIdTeam.text.toString().toInt()

            viewModel.addOrUpdatePlayer(name = name, cpf = cpf, bornAt = bornAt, idTeam = teamId, idPlayer = args.player?.idPlayer ?: 0)
        }

        btn_delete_player.setOnClickListener{
            viewModel.remotePlayer(idPlayer = args.player?.idPlayer ?: 0)
        }
    }
}