package com.example.kotlin6.presenter

import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameEditView

class GameEditPresenter(private val view: GameEditView) {

    private val games = mutableListOf(
        Game("The Witcher 3", "An open-world RPG", "RPG", 2015),
        Game("Cyberpunk 2077", "A futuristic RPG", "RPG", 2020),
        Game("Minecraft", "A sandbox game", "Sandbox", 2011)
    )

    fun loadGameDetails(position: Int) {
        val game = games.getOrNull(position)
        game?.let { view.showGameDetails(it) }
    }

    fun saveGameDetails(position: Int, title: String, description: String, genre: String, year: Int) {
        if (title.isEmpty()) {
            view.showError("Название обязательно")
            return
        }
        if (description.isEmpty()) {
            view.showError("Описание обязательно")
            return
        }

        val updatedGame = Game(title, description, genre, year)

        // Обновляем игру в списке
        if (position in games.indices) {
            games[position] = updatedGame
            view.navigateBack()  // Возвращаемся назад
        } else {
            view.showError("Игра не найдена")
        }
    }
}
