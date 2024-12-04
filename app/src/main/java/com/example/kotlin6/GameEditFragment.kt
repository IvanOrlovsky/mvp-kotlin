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
import com.example.kotlin6.presenter.GameEditPresenter
import com.example.kotlin6.view.GameEditView

class GameEditFragment : Fragment(), GameEditView {

    private lateinit var presenter: GameEditPresenter
    private val args: GameEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_edit, container, false)

        presenter = GameEditPresenter(this)

        val titleEditText = view.findViewById<EditText>(R.id.editTitle)
        val descriptionEditText = view.findViewById<EditText>(R.id.editDescription)
        val genreEditText = view.findViewById<EditText>(R.id.editGenre)
        val yearEditText = view.findViewById<EditText>(R.id.editYear)

        val position = args.position
        presenter.loadGameDetails(position)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            presenter.saveGameDetails(position, title, description, genreEditText.text.toString().trim(), yearEditText.text.toString().toIntOrNull() ?: 0)
        }

        return view
    }

    override fun showGameDetails(game: Game) {
        view?.findViewById<EditText>(R.id.editTitle)?.setText(game.title)
        view?.findViewById<EditText>(R.id.editDescription)?.setText(game.description)
        view?.findViewById<EditText>(R.id.editGenre)?.setText(game.genre)
        view?.findViewById<EditText>(R.id.editYear)?.setText(game.year.toString())
    }

    override fun showError(message: String) {
        // Показать ошибку (например, через Toast)
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }
}
