package com.example.kotlin6.view

import com.example.kotlin6.data.Game

interface GameEditView {
    fun showGameDetails(game: Game)
    fun showError(message: String)
    fun navigateBack()
}
