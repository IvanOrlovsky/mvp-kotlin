package com.example.kotlin6.view

import com.example.kotlin6.data.Game

interface GameDetailView {
    fun showGameDetails(game: Game)
    fun showError(message: String)
    fun navigateToEditScreen(game: Game, position: Int)
}
