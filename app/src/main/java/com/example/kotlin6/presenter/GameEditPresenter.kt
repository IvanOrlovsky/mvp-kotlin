package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameEditView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameEditPresenter(
    private val view: GameEditView, // Интерфейс, который реализует View для взаимодействия с фрагментом редактирования
    private val context: Context // Контекст приложения для работы с SharedPreferences
) {

    private val sharedPrefs = context.getSharedPreferences("games_data", Context.MODE_PRIVATE)
    // SharedPreferences для сохранения и загрузки списка игр

    // Загружает список игр из SharedPreferences
    private fun getGamesFromPrefs(): MutableList<Game> {
        val json = sharedPrefs.getString("games_list", "[]") ?: "[]" // Получает строку JSON или пустой список по умолчанию
        val type = object : TypeToken<List<Game>>() {}.type // Определяет тип данных для десериализации
        return Gson().fromJson(json, type) // Конвертирует JSON в список объектов Game
    }

    // Сохраняет обновленный список игр в SharedPreferences
    private fun saveGamesToPrefs(games: List<Game>) {
        val json = Gson().toJson(games) // Конвертирует список игр в JSON
        sharedPrefs.edit().putString("games_list", json).apply() // Сохраняет JSON в SharedPreferences
    }

    // Загружает детали игры по позиции и передает их во View для отображения
    fun loadGameDetails(position: Int) {
        val games = getGamesFromPrefs()
        if (position in games.indices) {
            val game = games[position]
            view.showGameDetails(game) // Отправляем данные игры в View для отображения
        } else {
            view.navigateBack() // Если игры нет, возвращаемся на предыдущий экран
        }
    }

    // Сохраняет обновленные данные игры и обновляет список в SharedPreferences
    fun saveGameDetails(position: Int, title: String, description: String, genre: String, year: Int) {
        val games = getGamesFromPrefs()
        if (position in games.indices) {
            val updatedGame = Game(title, description, genre, year)
            games[position] = updatedGame
            saveGamesToPrefs(games)
            view.returnUpdatedGame(updatedGame, position) // Сообщаем фрагменту, что игра обновлена
        }
    }

}
