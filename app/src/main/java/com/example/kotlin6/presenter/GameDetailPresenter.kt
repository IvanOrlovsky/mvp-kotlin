package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.model.GameRepository
import com.example.kotlin6.view.GameDetailView

class GameDetailPresenter(
    private val view: GameDetailView,
    context: Context, // Контекст используется для инициализации репозитория
    private val position: Int // Позиция игры для отображения
) {
    private val gameRepository = GameRepository(context) // Инициализация репозитория

    fun loadGameDetails() {
        val games = gameRepository.getGames()
        if (position in games.indices) {
            val game = games[position]
            view.showGameDetails(game)
        } else {
            // Если игра не найдена, можно реализовать обработку ошибки
        }
    }

    fun onEditGameClicked() {
        val games = gameRepository.getGames()
        if (position in games.indices) {
            val game = games[position]
            view.navigateToEditScreen(game, position)
        }
    }
}
