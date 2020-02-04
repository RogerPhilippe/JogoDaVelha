package br.com.philippesis.jogodavelha.models

import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private lateinit var game: Game
private lateinit var player: Player
private lateinit var anotherPlayer: Player

class GameTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {
        game = Game("Amanda", "Carolina")
        player = game.player1
        anotherPlayer = game.player2
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromLeft() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        Assert.assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameHorizontalCellsAtRow1() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell
        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()
        assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    fun endGameIfBoardIsFull() {
        val cell1 = Cell(Player("1", "x"))
        val cell2 = Cell(Player("2", "o"))
        val cell3 = Cell(Player("1", "x"))
        val cell4 = Cell(Player("2", "o"))
        val cell5 = Cell(Player("1", "x"))
        val cell6 = Cell(Player("2", "o"))
        val cell7 = Cell(Player("1", "x"))
        val cell8 = Cell(Player("2", "o"))
        val cell9 = Cell(Player("1", "x"))
        game.cells[0][0] = cell1
        game.cells[0][1] = cell2
        game.cells[0][2] = cell3
        game.cells[1][0] = cell4
        game.cells[1][1] = cell5
        game.cells[1][2] = cell6
        game.cells[2][0] = cell7
        game.cells[2][1] = cell8
        game.cells[2][2] = cell9
        val isBoardFull = game.isBoardFull
        assertTrue(isBoardFull)
    }

}
