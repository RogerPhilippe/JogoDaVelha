package br.com.philippesis.jogodavelha.views.game

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.philippesis.jogodavelha.models.Cell
import br.com.philippesis.jogodavelha.models.Game
import br.com.philippesis.jogodavelha.models.Player
import br.com.philippesis.jogodavelha.utils.StringUtility.stringFromNumbers

class GameViewModel : ViewModel() {

    lateinit var cells: ObservableArrayMap<String, String>

    private lateinit var game: Game

    val winner: LiveData<Player>
        get() = game.winner

    fun init(player1: String, player2: String) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = Cell(game.currentPlayer)
            cells[stringFromNumbers(row, column)] = game.currentPlayer.value
            if (game.hasGameEnded())
                game.reset()
            else
                game.switchPlayer()
        }
    }
}