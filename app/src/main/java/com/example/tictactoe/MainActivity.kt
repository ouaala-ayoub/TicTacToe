package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

//interface OptionsClickListener{
//    fun OnClickListener()
//}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startGame.setOnClickListener {
            startGameActivity()
        }
        binding.options.setOnClickListener {
            startOptionsDialog()
        }
        binding.quitGame.setOnClickListener {
            quitGame()
        }
    }

    private fun startOptionsDialog() {
        val title = resources.getString(R.string.options_title)
        val radioGroup = R.layout.options

        startDialog(radioGroup, title, View.OnClickListener {
            setUpTheOptionsDialog(radioGroup)
        })
    }

    private fun startDialog(view: Int, title: String, onStartClickListener: View.OnClickListener) {

        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("cancel", null)
            .setPositiveButton("start") { _, _ ->
                val layoutView = LayoutInflater.from(this).inflate(view, null)
                onStartClickListener.onClick(layoutView)
            }.show()
    }

    private fun startGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun quitGame() {
        //finish()
    }
    private fun setUpTheOptionsDialog(radioLayout: Int){
        val radioOptions = LayoutInflater.from(this).inflate(radioLayout, null)
        val radioGroup = radioOptions.findViewById<RadioGroup>(R.id.options)
        val pvp = radioOptions.findViewById<RadioButton>(R.id.pvp)
        val pvai = radioOptions.findViewById<RadioButton>(R.id.pvai)

        radioOptions.setOnClickListener {
            if(pvp.isChecked) {
                pvai.isChecked = !pvai.isChecked
            }
            if(pvai.isChecked) {
                pvp.isChecked = !pvp.isChecked
            }
        }

        when(radioGroup.checkedRadioButtonId){
            R.id.pvp -> {
                // player vs player
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
            R.id.pvai -> {
                // player vs AI
                //to Implement
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }
    }

}