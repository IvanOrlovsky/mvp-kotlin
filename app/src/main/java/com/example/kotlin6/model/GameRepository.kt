package com.example.kotlin6.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameRepository(private val context: Context) {

    private val sharedPrefs by lazy { context.getSharedPreferences("games_data", Context.MODE_PRIVATE) }

    fun getGames(): MutableList<Game> {
        val json = sharedPrefs.getString("games_list", "[]") ?: "[]"
        val type = object : TypeToken<List<Game>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun saveGames(games: List<Game>) {
        val json = Gson().toJson(games)
        sharedPrefs.edit().putString("games_list", json).apply()
    }

    fun addGame(game: Game) {
        val games = getGames()
        games.add(game)
        saveGames(games)
    }

    fun removeGame(position: Int) {
        val games = getGames()
        if (position in games.indices) {
            games.removeAt(position)
            saveGames(games)
        }
    }
}
