package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityGamefinishBinding

class GamefinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamefinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGamefinishBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val winner = intent.getSerializableExtra("winner") as String
        val winnerFullText: String = if (winner == "draw") winner
        else{
            resources.getString(R.string.winner_is, winner)
        }
        binding.winner.text = winnerFullText

        binding.restart.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.gotoMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}