package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin6.data.Game
import com.example.kotlin6.databinding.FragmentGameListBinding
import com.example.kotlin6.presenter.GameListPresenter
import com.example.kotlin6.view.GameListView
import androidx.navigation.fragment.findNavController

class GameListFragment : Fragment(), GameListView {

    private lateinit var binding: FragmentGameListBinding // Привязка к элементам UI
    private lateinit var gamePresenter: GameListPresenter // Презентер для работы с данными игр
    private lateinit var gameAdapter: GameAdapter // Адаптер для отображения списка игр

    // Метод создания фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListBinding.inflate(inflater, container, false)

        // Инициализация презентера
        gamePresenter = GameListPresenter(this, requireContext())

        // Настройка RecyclerView для отображения списка игр
        gameAdapter = GameAdapter(
            mutableListOf(),
            { game, position ->
                val action = GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game, position)
                findNavController().navigate(action)
            },
            { position -> gamePresenter.removeGame(position) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = gameAdapter

        // Загрузка данных игр из хранилища
        gamePresenter.loadGames()

        // Обработчик клика по кнопке добавления игры
        binding.addButton.setOnClickListener {
            val newGame = Game("New Game", "Description", "Genre", 2022)
            gamePresenter.addGame(newGame) // Добавление новой игры
        }

        return binding.root
    }

    // Метод для отображения списка игр
    override fun showGames(games: List<Game>) {
        gameAdapter.updateGames(games) // Обновление адаптера с новыми данными
    }
}
