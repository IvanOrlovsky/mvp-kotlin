package com.example.kotlin6.presenter

import android.content.Context
import com.example.kotlin6.data.Game
import com.example.kotlin6.view.GameListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameListPresenter(
    private val view: GameListView, // Интерфейс, реализующий View для отображения списка игр
    private val context: Context // Контекст приложения для работы с SharedPreferences
) {

    private val sharedPrefs = context.getSharedPreferences("games_data", Context.MODE_PRIVATE)
    // SharedPreferences для сохранения и загрузки списка игр

    // Загружает список игр из SharedPreferences и передает их в View
    fun loadGames() {
        val json = sharedPrefs.getString("games_list", "[]") // Получает JSON-строку из SharedPreferences
        val type = object : TypeToken<List<Game>>() {}.type // Определяет тип данных для десериализации
        val games: List<Game> = Gson().fromJson(json, type) // Конвертирует JSON в список объектов Game
        view.showGames(games) // Передает список игр в View для отображения
    }

    // Добавляет новую игру в список, сохраняет изменения и обновляет View
    fun addGame(game: Game) {
        val games = getGamesFromPrefs().toMutableList() // Загружает текущий список игр
        games.add(game) // Добавляет новую игру
        saveGamesToPrefs(games) // Сохраняет обновленный список в SharedPreferences
        view.showGames(games) // Обновляет отображение в View
    }

    // Удаляет игру из списка по позиции, сохраняет изменения и обновляет View
    fun removeGame(position: Int) {
        val games = getGamesFromPrefs().toMutableList()
        if (position in games.indices) {
            games.removeAt(position)
            saveGamesToPrefs(games)
            view.showGames(games)
        }
    }

    // Загружает список игр из SharedPreferences
    private fun getGamesFromPrefs(): List<Game> {
        val json = sharedPrefs.getString("games_list", "[]") // Получает JSON-строку из SharedPreferences
        val type = object : TypeToken<List<Game>>() {}.type // Определяет тип данных для десериализации
        return Gson().fromJson(json, type) // Конвертирует JSON в список объектов Game
    }

    // Сохраняет список игр в SharedPreferences
    private fun saveGamesToPrefs(games: List<Game>) {
        val json = Gson().toJson(games)
        sharedPrefs.edit().putString("games_list", json).apply()
    }
}
