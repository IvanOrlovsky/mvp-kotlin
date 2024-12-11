package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.model.Game
import com.example.kotlin6.model.GameRepository
import com.example.kotlin6.view.GameListView

class GameListPresenter(
    private val view: GameListView,
    context: Context
) {
    private val gameRepository = GameRepository(context)

    fun loadGames() {
        val games = gameRepository.getGames()
        view.showGames(games)
    }

    fun addGame(game: Game) {
        gameRepository.addGame(game)
        view.showGames(gameRepository.getGames())
    }

    fun removeGame(position: Int) {
        gameRepository.removeGame(position)
        view.showGames(gameRepository.getGames())
    }
}
