package com.example.kotlin6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin6.data.Game

class GameAdapter(
    private var games: List<Game>,
    private val onItemClick: (Game, Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.gameTitle)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.title.text = game.title
        holder.itemView.setOnClickListener { onItemClick(game, position) }
        holder.deleteIcon.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = games.size

    // Метод для обновления данных
    fun updateGames(newGames: List<Game>) {
        this.games = newGames
        notifyDataSetChanged()
    }
}
