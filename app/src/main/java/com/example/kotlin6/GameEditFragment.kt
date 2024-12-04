package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin6.data.Game
import com.example.kotlin6.databinding.FragmentGameEditBinding
import com.example.kotlin6.presenter.GameEditPresenter
import com.example.kotlin6.view.GameEditView

class GameEditFragment : Fragment(), GameEditView {

    private lateinit var presenter: GameEditPresenter // Презентер для редактирования игры
    private lateinit var binding: FragmentGameEditBinding // Для привязки UI-элементов
    private val args: GameEditFragmentArgs by navArgs() // Получаем аргументы, переданные в фрагмент

    // Метод создания фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameEditBinding.inflate(inflater, container, false)

        // Инициализация презентера
        presenter = GameEditPresenter(this, requireContext())

        // Загружаем текущие данные игры по переданной позиции
        presenter.loadGameDetails(args.position)

        // Слушатель на кнопку сохранения
        binding.saveButton.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()
            val description = binding.editDescription.text.toString().trim()
            val genre = binding.editGenre.text.toString().trim()
            val year = binding.editYear.text.toString().toIntOrNull() ?: 0

            // Сохраняем обновленные данные игры
            presenter.saveGameDetails(args.position, title, description, genre, year)
        }

        return binding.root
    }

    // Отображение данных игры в форме
    override fun showGameDetails(game: Game) {
        binding.editTitle.setText(game.title)
        binding.editDescription.setText(game.description)
        binding.editGenre.setText(game.genre)
        binding.editYear.setText(game.year.toString())
    }

    // Метод для возврата обновленных данных обратно в предыдущий фрагмент
    override fun returnUpdatedGame(game: Game, position: Int) {
        val navController = findNavController()
        // Устанавливаем обновленные данные в savedStateHandle для передачи в предыдущий фрагмент
        navController.previousBackStackEntry?.savedStateHandle?.set("updatedGame", game)
        navController.previousBackStackEntry?.savedStateHandle?.set("updatedPosition", position)
        navigateBack()
    }

    // Метод для навигации назад
    override fun navigateBack() {
        findNavController().popBackStack()
    }
}
