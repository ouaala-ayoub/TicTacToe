package modules

import android.util.Log
private val Tag = "TicGameModule"

class TicGameModule(
    private val squareList: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
    private var moves: Int = 0,
    private var isFirstPlayerTurn: Boolean = true,
    private var winner: String = "not yet"
) {
    fun checkForWin(): Boolean{
        val indexesVer = listOf(0, 3, 6)
        val l = squareList
        for (i in indexesVer){
            if (l[i] == 1 && l[i+1] == 1 && l[i+2] == 1 || l[i] == 2 && l[i+1] == 2 && l[i+2] == 2) {
                setWinner()
                return true
            }
        }
        for (i in 0..2){
            if (l[i] == 1 && l[i+3] == 1 && l[i+6] == 1 || l[i] == 2 && l[i+3] == 2 && l[i+6] == 2) {
                setWinner()
                return true
            }
        }
        if (l[0] == 1 && l[4] == 1 && l[8] == 1 || l[0] == 2 && l[4] == 2 && l[8] == 2) {
            setWinner()
            return true
        }
        if (l[2] == 1 && l[4] == 1 && l[6] == 1 || l[2] == 2 && l[4] == 2 && l[6] == 2) {
            setWinner()
            return true
        }
        return false
    }
    fun checkForDraw() = moves == 9

    fun getIsFirstPlayerTurn() = isFirstPlayerTurn
    fun changeTurns(){
        isFirstPlayerTurn = !isFirstPlayerTurn
    }
    fun addMove(){
        moves++
//        Log.i(Tag, moves.toString())
    }
    private fun setWinner(){
        winner = if (isFirstPlayerTurn) "player1"
        else "player2"
    }
    fun getWinner(): String = winner
    fun getMoves(): Int = moves
    fun updateGame(position: Int, image: ImageTic){
        if(isFirstPlayerTurn) squareList[position] = 1
        else squareList[position] = 2
        image.isFlipped = true
    }
    fun isValidMove(image: ImageTic): Boolean{
        return !image.isFlipped
    }
}