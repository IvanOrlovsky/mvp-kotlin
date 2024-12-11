package com.example.kotlin6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin6.model.Game

// Адаптер для отображения списка игр в RecyclerView
class GameAdapter(
    private var games: MutableList<Game>, // Список игр
    private val onItemClick: (Game, Int) -> Unit, // Обработчик клика по элементу
    private val onDeleteClick: (Int) -> Unit // Обработчик клика по кнопке удаления
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    // ViewHolder для отдельного элемента списка
    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.gameTitle)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    // Создание ViewHolder при необходимости (при прокрутке списка)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    // Привязка данных из списка к элементам UI
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.title.text = game.title
        holder.itemView.setOnClickListener { onItemClick(game, position) }
        holder.deleteIcon.setOnClickListener {
            onDeleteClick(position)
        }
    }

    // Количество элементов в списке
    override fun getItemCount() = games.size

    // Обновление списка игр
    fun updateGames(games: List<Game>) {
        this.games = games.toMutableList() // Обновляем данные
        notifyDataSetChanged() // Уведомляем адаптер о изменении данных
    }
}
