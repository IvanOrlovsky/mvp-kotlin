package com.example.kotlin6.presenter

import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameDetailView

class GameDetailPresenter(private val view: GameDetailView) {

    // Пример данных игры
    private var currentGame: Game? = null

    fun loadGameDetails(game: Game) {
        currentGame = game
        view.showGameDetails(game)  // Отображение данных игры в представлении
    }

    fun onEditButtonClicked(position: Int) {
        currentGame?.let {
            view.navigateToEditScreen(it, position)  // Переход на экран редактирования
        } ?: view.showError("Game details not available")  // Ошибка, если данных нет
    }

    fun onError(message: String) {
        view.showError(message)  // Отображение ошибки
    }
}
