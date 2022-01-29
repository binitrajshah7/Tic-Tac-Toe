package com.gmail.binitrajshah.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener{

    var player = true
    var turnCount = 0
    var boardStatus = Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for(i in board) {
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        // initializing board
        initializeBoardStatus()

        resetBtn.setOnClickListener{
            initializeBoardStatus()
            updateDisplay("Player X Turn")
            turnCount = 0;
            player = true;
        }

    }

    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true;
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.button1 -> {
                updateValue(row = 0, col = 0, PLAYER = player)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, PLAYER = player)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, PLAYER = player)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, PLAYER = player)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, PLAYER = player)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, PLAYER = player)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, PLAYER = player)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, PLAYER = player)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, PLAYER = player)
            }
        }

        player = !player
        turnCount++;


        // changing display
        if(player){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O turn")
        }

        if(turnCount == 9)
        {
            updateDisplay("Game Draw!")
        }

        checkWinner()

    }

    private fun updateDisplay(displayText: String) {
        turnDisplay.text = displayText
        if(displayText.contains("Winner")){
            for(i in board) {
                for(button in i){
                    button.isEnabled = false;
                }
            }
        }
    }

    private fun updateValue(row: Int, col: Int, PLAYER: Boolean) {

            val text = if(PLAYER) "X" else "O"
            val value = if(PLAYER) 1 else 0
            board[row][col].apply {
                isEnabled = false;
                setText(text)
            }

            boardStatus[row][col] = value;
    }

    private fun checkWinner() {
        // horizontal rows
        for(i in 0..2) {
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if(boardStatus[i][0] == 1) {
                    updateDisplay("Congratulations, Player X is Winner")
                    break
                }
                else if(boardStatus[i][0] == 0) {
                    updateDisplay("Congratulations, Player O is Winner")
                    break
                }
            }
        }

        // vertical columns
        for(i in 0..2) {
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if(boardStatus[0][i] == 1) {
                    updateDisplay("Congratulations, Player X is Winner")
                    break
                }
                else if(boardStatus[0][i] == 0) {
                    updateDisplay("Congratulations, Player O is Winner")
                    break
                }
            }
        }

        // diagonal first
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if(boardStatus[0][0] == 1) {
                updateDisplay("Congratulations, Player X is Winner")
            }
            else if(boardStatus[0][0] == 0) {
                updateDisplay("Congratulations, Player O is Winner")
            }
        }

        // diagonal second
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if(boardStatus[0][2] == 1) {
                updateDisplay("Congratulations, Player X is Winner")
            }
            else if(boardStatus[0][2] == 0) {
                updateDisplay("Congratulations, Player O is Winner")
            }
        }
    }
}
