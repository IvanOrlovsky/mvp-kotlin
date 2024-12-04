package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin6.data.Game
import com.example.kotlin6.databinding.FragmentGameDetailBinding
import com.example.kotlin6.presenter.GameDetailPresenter
import com.example.kotlin6.view.GameDetailView

class GameDetailFragment : Fragment(), GameDetailView {

    private val args: GameDetailFragmentArgs by navArgs() // Получаем аргументы, переданные в фрагмент
    private lateinit var binding: FragmentGameDetailBinding // Для привязки UI-элементов
    private lateinit var gameDetailPresenter: GameDetailPresenter // Презентер для логики отображения данных

    // Метод создания фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)

        // Инициализация презентера
        gameDetailPresenter = GameDetailPresenter(this, requireContext())

        // Отображение первоначальных данных игры
        showGameDetails(args.game)

        // Логика обновления данных при возвращении из редактирования
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Game>("updatedGame")
            ?.observe(viewLifecycleOwner) { updatedGame ->
                showGameDetails(updatedGame) // Обновляем UI с новыми данными
            }

        // Навигация на экран редактирования игры
        binding.editButton.setOnClickListener {
            val action = GameDetailFragmentDirections.actionGameDetailFragmentToGameEditFragment(
                args.game,
                args.position
            )
            findNavController().navigate(action)
        }

        return binding.root
    }

    // Отображение подробностей игры в UI
    override fun showGameDetails(game: Game) {
        binding.detailTitle.text = game.title
        binding.detailDescription.text = game.description
        binding.detailGenre.text = game.genre
        binding.detailYear.text = game.year.toString()
    }

    // Переход на экран редактирования
    override fun navigateToEditScreen(game: Game, position: Int) {
        val action =
            GameDetailFragmentDirections.actionGameDetailFragmentToGameEditFragment(game, position)
        findNavController().navigate(action)
    }
}
