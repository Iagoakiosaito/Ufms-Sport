package com.example.controledejogos.add.team

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
import com.example.controledejogos.data.dao.GamesDao
import com.example.controledejogos.data.database.GamesDatabase
import com.example.controledejogos.extensions.hideKeyboard
import com.example.controledejogos.repository.DatabaseDataSource
import com.example.controledejogos.repository.IGamesRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_team_fragment.*

class AddTeamFragment : Fragment() {

    private lateinit var v : View

    private val viewModel: AddTeamViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val dao: GamesDao = GamesDatabase.getDatabase(requireContext()).gamesDao()

                val repository: IGamesRepository = DatabaseDataSource(dao)

                return AddTeamViewModel(repository) as T
            }
        }
    }

    private val args: AddTeamFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.add_team_fragment, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.team?.let { team ->
            edtTeamDescription.setText(team.description)
            btnSaveTeam.text = R.string.team_button_upadte.toString()
        }

        createObservers()
        setListeners()
    }

    private fun createObservers() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { teamState ->
            when(teamState) {
                is AddTeamViewModel.TeamState.Inserted -> {
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
        edtTeamDescription.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        btnSaveTeam.setOnClickListener(){
            viewModel.saveTeam(edtTeamDescription.text.toString())
        }
    }

}