package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameDetailView

class GameDetailPresenter(
    private val view: GameDetailView, // Интерфейс, который реализует View
    private val context: Context // Контекст приложения для доступа к SharedPreferences
) {

    fun loadGameDetails(game: Game) {
        view.showGameDetails(game)
    }

    fun onEditGameClicked(game: Game, position: Int) {
        view.navigateToEditScreen(game, position)
    }
}
