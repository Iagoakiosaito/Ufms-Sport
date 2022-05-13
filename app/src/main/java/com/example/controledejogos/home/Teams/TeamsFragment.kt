package com.example.controledejogos.home.Teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.controledejogos.R
import com.example.controledejogos.add.team.AddTeamViewModel
import com.example.controledejogos.data.dao.GamesDao
import com.example.controledejogos.data.database.GamesDatabase
import com.example.controledejogos.data.entity.Team
import com.example.controledejogos.extensions.navigateWithAnimations
import com.example.controledejogos.home.Teams.adapter.TeamsListRecyclerViewAdapter
import com.example.controledejogos.repository.DatabaseDataSource
import com.example.controledejogos.repository.IGamesRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.teams_fragment.*


class TeamsFragment : Fragment() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),
        R.anim.rotate_open_anim
    ) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),
        R.anim.rotate_close_anim
    ) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),
        R.anim.from_bottom_anim
    ) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),
        R.anim.to_bottom_anim
    ) }
    private var clicked : Boolean = false

    private lateinit var v : View
    private lateinit var fabMenu : FloatingActionButton
    private lateinit var fabAddPlayer : FloatingActionButton
    private lateinit var fabAddTeam : FloatingActionButton

    private val viewModel: TeamsViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val dao: GamesDao = GamesDatabase.getDatabase(requireContext()).gamesDao()

                val repository: IGamesRepository = DatabaseDataSource(dao)

                return TeamsViewModel(repository) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.teams_fragment, container, false)

        defineItems(v)
        defineButtons()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()

    }

    private fun observeViewModelEvents() {

        viewModel.allTeamsEvent.observe(viewLifecycleOwner) {

            val teamsListRecyclerViewAdapter = TeamsListRecyclerViewAdapter(it)

            recyclerview_teams.run {
                setHasFixedSize(true)
                adapter = teamsListRecyclerViewAdapter
            }
        }

    }

    private fun defineItems(v: View?) {
        fabMenu = v!!.findViewById(R.id.fabMenu)
        fabAddPlayer = v!!.findViewById(R.id.fabAddPlayer)
        fabAddTeam = v!!.findViewById(R.id.fabAddTeam)
    }

    private fun defineButtons() {
        val navController = findNavController()
        fabMenu.setOnClickListener{
            onMenuButtonClicked()
        }
        fabAddPlayer.setOnClickListener {
            val action = R.id.action_gamesFragment_to_addPlayer
            navController.navigateWithAnimations(action)
        }
        fabAddTeam.setOnClickListener {
            val action = R.id.action_gamesFragment_to_addTeamFragment
            navController.navigateWithAnimations(action)
        }

    }

    private fun onMenuButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            fabAddPlayer.visibility = View.VISIBLE
            fabAddTeam.visibility = View.VISIBLE
        } else {
            fabAddPlayer.visibility = View.INVISIBLE
            fabAddTeam.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            fabAddTeam.startAnimation(fromBottom)
            fabAddPlayer.startAnimation(fromBottom)
            fabMenu.startAnimation(rotateOpen)
        }else{
            fabAddTeam.startAnimation(toBottom)
            fabAddPlayer.startAnimation(toBottom)
            fabMenu.startAnimation(rotateClose)
        }
    }


}