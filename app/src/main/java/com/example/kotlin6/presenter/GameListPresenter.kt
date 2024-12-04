package com.example.kotlin6.presenter

import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameListView

class GameListPresenter(private val view: GameListView) {

    private val games = mutableListOf<Game>()

    init {
        // Добавление тестовых данных
        games.add(Game("The Witcher 3", "An open-world RPG", "RPG", 2015))
        games.add(Game("Cyberpunk 2077", "A futuristic RPG", "RPG", 2020))
        games.add(Game("Minecraft", "A sandbox game", "Sandbox", 2011))
    }

    fun loadGames() {
        // Загружаем игры (например, из репозитория)
        view.showGames(games)
    }

    fun addGame(game: Game) {
        games.add(game)
        loadGames()
    }

    fun removeGame(position: Int) {
        if (position in games.indices) {
            games.removeAt(position)
            loadGames()
        }
    }
}
