package br.com.philippesis.jogodavelha.views

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.philippesis.jogodavelha.R
import br.com.philippesis.jogodavelha.databinding.ActivityGameBinding
import br.com.philippesis.jogodavelha.models.Player
import br.com.philippesis.jogodavelha.views.game.GameBeginDialog
import br.com.philippesis.jogodavelha.views.game.GameEndDialog
import br.com.philippesis.jogodavelha.views.game.GameViewModel

class GameActivity : AppCompatActivity() {
    lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        promptForPlayers()
    }
    fun promptForPlayers() {
        val dialog = GameBeginDialog.newInstance(this)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }
    fun onPlayersSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }

    private fun initDataBinding(player1: String, player2: String) {
        val activityGameBinding =
            DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }
    private fun setUpOnGameEndListener() {
        gameViewModel.winner.observe(this, Observer { this.onGameWinnerChanged(it) })
    }

    @VisibleForTesting
    fun onGameWinnerChanged(winner: Player?) {
        val winnerName = if (winner == null || winner.name.isEmpty()) NO_WINNER else
            winner.name
        val dialog = GameEndDialog.newInstance(this, winnerName)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }
    companion object {
        private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
        private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
        private val NO_WINNER = "No one"
    }
}
