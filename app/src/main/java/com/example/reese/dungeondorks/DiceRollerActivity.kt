package com.example.reese.dungeondorks

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class DiceRollerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roller)

        // Grab roll button
        val btn = findViewById<Button>(R.id.btnRoll)

        // Set the button up to roll a d20!
        btn.setOnClickListener {
            rollDice(Integer.valueOf(20))
        }
    }

    private fun rollDice(bound: kotlin.Int) {

        // Grab the results box, the modifier input, and roll the die!
        val txt = findViewById<TextView>(R.id.txtRollResult)
        val mod = findViewById<EditText>(R.id.edtModifier).text.toString()
        val diceResult = Random().nextInt(bound) + 1

        // Green on critical success, red on critical failure, black otherwise
        when {
            diceResult >= 20 -> txt.setTextColor(Color.GREEN)
            diceResult <= 1 -> txt.setTextColor(Color.RED)
            else -> txt.setTextColor(Color.BLACK)
        }

        // Display the result!
        val strResult = "Rolled a " + ( diceResult + if(mod.isNullOrEmpty()) 0 else mod.toInt() )
        txt.text = strResult
    }
}