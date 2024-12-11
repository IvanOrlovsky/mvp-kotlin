package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.model.Game
import com.example.kotlin6.model.GameRepository
import com.example.kotlin6.view.GameEditView

class GameEditPresenter(
    private val view: GameEditView,
    context: Context, // Контекст используется для инициализации репозитория
    private val position: Int // Позиция игры для редактирования
) {
    private val gameRepository = GameRepository(context) // Инициализация репозитория

    fun loadGameDetails() {
        val games = gameRepository.getGames()
        if (position in games.indices) {
            val game = games[position]
            view.showGameDetails(game)
        } else {
            view.navigateBack()
        }
    }

    fun saveGameDetails(title: String, description: String, genre: String, year: Int) {
        val games = gameRepository.getGames()
        if (position in games.indices) {
            val updatedGame = Game(title, description, genre, year)
            games[position] = updatedGame
            gameRepository.saveGames(games)
            view.returnUpdatedGame(updatedGame, position)
        }
    }
}
