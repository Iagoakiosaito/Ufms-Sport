package com.example.controledejogos.home.Teams.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.controledejogos.R
import com.example.controledejogos.data.entity.Team
import com.example.controledejogos.databinding.RecyclerviewTeamsBinding
import kotlinx.android.synthetic.main.recyclerview_teams.view.*

class TeamsListRecyclerViewAdapter(
    private var teams : List<Team>,
) : RecyclerView.Adapter<TeamsListRecyclerViewAdapter.TeamsViewHolder>(){

    private val listPositions : ArrayList<Int> = ArrayList()

//    inner class TeamsViewHolder(
//        val binding : RecyclerviewTeamsBinding
//    ): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = teams.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TeamsViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_teams, parent, false)

        return TeamsViewHolder(view)

    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        if (teams.isNotEmpty()){
            holder.bindView(teams[position])
        }
    }

    class TeamsViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val txtTeamDescription: TextView = itemView.txt_description
        private val txtTeamId: TextView = itemView.txt_idTeam

        fun bindView(team: Team){
            txtTeamDescription.text = team.description
            txtTeamId.text = team.idTeam.toString()
        }
    }

}