package com.example.kotlin6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin6.data.Game

class GameViewModel : ViewModel() {
    private val _games = MutableLiveData<MutableList<Game>>().apply {
        value = mutableListOf(
            Game("The Witcher 3", "An open-world RPG", "RPG", 2015),
            Game("Cyberpunk 2077", "A futuristic RPG", "RPG", 2020),
            Game("Minecraft", "A sandbox game", "Sandbox", 2011)
        )
    }
    val games: LiveData<MutableList<Game>> = _games

    fun addGame(game: Game) {
        _games.value = (_games.value ?: mutableListOf()).apply {
            add(game)
        }
    }

    fun updateGame(position: Int, updatedGame: Game) {
        _games.value = _games.value?.apply {
            if (position in indices) {
                this[position] = updatedGame
            }
        }
    }

    fun removeGame(position: Int) {
        _games.value = _games.value?.apply {
            if (position in indices) {
                removeAt(position)
            }
        }
    }
}
