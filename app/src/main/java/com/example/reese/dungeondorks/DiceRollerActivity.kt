package com.example.reese.dungeondorks

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class DiceRollerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roller)

        val btn = findViewById<Button>(R.id.btnRoll)

        btn.setOnClickListener {
            rollDice(Integer(20), Integer(0))
        }
    }

    private fun rollDice(bound: Integer, bonus: Integer) {

        val txt = findViewById<TextView>(R.id.txtRollResult)

        val diceResult = Random().nextInt(bound.toInt()) + 1

        when {
            diceResult >= 20 -> txt.setTextColor(Color.GREEN)
            diceResult <= 1 -> txt.setTextColor(Color.RED)
            else -> txt.setTextColor(Color.BLACK)
        }
        val strResult = "Rolled a " + ( diceResult + bonus.toInt() )

        txt.text = strResult
    }
}