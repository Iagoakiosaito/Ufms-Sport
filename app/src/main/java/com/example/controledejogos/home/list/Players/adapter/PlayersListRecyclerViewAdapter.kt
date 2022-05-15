package com.example.controledejogos.home.list.Players.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controledejogos.R
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.home.list.Players.adapter.PlayersListRecyclerViewAdapter.PlayersViewHolder
import kotlinx.android.synthetic.main.recyclerview_players.view.*

class PlayersListRecyclerViewAdapter(
    private var players : List<Player>,
) : RecyclerView.Adapter<PlayersViewHolder>() {

    var onItemClick: ((player: Player) -> Unit)? = null

    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PlayersViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_players, parent, false)

        return PlayersViewHolder(view)

    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        if (players.isNotEmpty()){
            holder.bindView(players[position])
        }
    }

    inner class PlayersViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val txtPlayerName: TextView = itemView.txt_player_name
        private val txtPlayerBornAt: TextView = itemView.txt_player_born_at
        private val txtPlayerCpf: TextView = itemView.txt_player_cpf
        private val txtPlayerIdPlayer: TextView = itemView.txt_player_id_player


        fun bindView(player: Player){
            txtPlayerName.text = player.name
            txtPlayerBornAt.text = player.bornAt.toString()
            txtPlayerCpf.text = player.cpf
            txtPlayerIdPlayer.text = player.idPlayer.toString()

            itemView.setOnClickListener{
                onItemClick?.invoke(player)
            }
        }
    }

}