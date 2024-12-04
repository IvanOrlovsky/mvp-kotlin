package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin6.data.Game
import androidx.lifecycle.ViewModelProvider


class GameEditFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private val args: GameEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_edit, container, false)

        gameViewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        val titleEditText = view.findViewById<EditText>(R.id.editTitle)
        val descriptionEditText = view.findViewById<EditText>(R.id.editDescription)
        val genreEditText = view.findViewById<EditText>(R.id.editGenre)
        val yearEditText = view.findViewById<EditText>(R.id.editYear)

        val game = args.game
        val position = args.position

        titleEditText.setText(game.title)
        descriptionEditText.setText(game.description)
        genreEditText.setText(game.genre)
        yearEditText.setText(game.year.toString())

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (title.isEmpty()) {
                titleEditText.error = "Название обязательно"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                descriptionEditText.error = "Описание обязательно"
                return@setOnClickListener
            }

            val updatedGame = Game(
                title,
                description,
                genreEditText.text.toString().trim(),
                yearEditText.text.toString().toIntOrNull() ?: 0
            )

            gameViewModel.updateGame(position, updatedGame)

            findNavController().popBackStack()
        }


        return view
    }
}


