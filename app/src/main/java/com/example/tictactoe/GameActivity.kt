package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityGameBinding
import modules.ImageTic
import android.util.Log
import modules.TicGameModule

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val imagesList = mutableListOf<ImageTic>()
    private val Tag = "GameActivity"
    private val ticGame = TicGameModule()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityGameBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.i(Tag, ticGame.toString())
        binding.moves.text = resources.getString(R.string.moves, ticGame.getMoves().toString())
        setTheCage()
        imagesClickListener()
    }

    private fun getFittingMove(imageTic: ImageTic, firstPlayerTurn: Boolean): Int {
        return if(firstPlayerTurn){
                R.drawable.x
        } else R.drawable.o
    }

    private fun setTheCage() {
        imagesList.add(ImageTic(binding.btn1, 0))
        imagesList.add(ImageTic(binding.btn2, 1))
        imagesList.add(ImageTic(binding.btn3, 2))
        imagesList.add(ImageTic(binding.btn4, 3))
        imagesList.add(ImageTic(binding.btn5, 4))
        imagesList.add(ImageTic(binding.btn6, 5))
        imagesList.add(ImageTic(binding.btn7, 6))
        imagesList.add(ImageTic(binding.btn8, 7))
        imagesList.add(ImageTic(binding.btn9, 8))
    }

    private fun imagesClickListener(){
        for(image in imagesList){
            oneMove(image)
        }
    }
    private fun oneMove(image: ImageTic) {


        val img = image.imageView
        val x = image.position

        img.setOnClickListener{
            if(!ticGame.isValidMove(image)) return@setOnClickListener
            img.setImageResource(getFittingMove(image, ticGame.getIsFirstPlayerTurn()))
            ticGame.updateGame(x, image)
            ticGame.addMove()
            binding.moves.text = resources.getString(R.string.moves, ticGame.getMoves().toString())

            if (checkForEndGame()) {
                val intent = Intent(this, GamefinishActivity::class.java)
                if(ticGame.checkForWin()){
                    intent.putExtra("winner", ticGame.getWinner())
                }
                else intent.putExtra("winner", "draw")

                startActivity(intent)
                return@setOnClickListener
            }
            ticGame.changeTurns()

        }
    }
    private fun checkForEndGame(): Boolean{
        if(ticGame.checkForWin()) return true
        if(ticGame.checkForDraw()) return true
        return false
    }

}