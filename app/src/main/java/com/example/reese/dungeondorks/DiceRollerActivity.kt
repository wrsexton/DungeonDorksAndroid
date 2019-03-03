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

        val btn = findViewById<Button>(R.id.btnRoll)

        btn.setOnClickListener {
            rollDice(Integer.valueOf(20))
        }
    }

    private fun rollDice(bound: kotlin.Int) {

        val txt = findViewById<TextView>(R.id.txtRollResult)
        val bonus = findViewById<EditText>(R.id.edtModifier).text

        val diceResult = Random().nextInt(bound) + 1

        when {
            diceResult >= 20 -> txt.setTextColor(Color.GREEN)
            diceResult <= 1 -> txt.setTextColor(Color.RED)
            else -> txt.setTextColor(Color.BLACK)
        }
        val strResult = "Rolled a " + ( diceResult + bonus.toString().toInt() )

        txt.text = strResult
    }
}