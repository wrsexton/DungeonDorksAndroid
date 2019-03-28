package com.example.reese.dungeondorks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Grab references to the navigational buttons
        val btnDice = findViewById<Button>(R.id.btnDiceRoller)
        val btnSpellBook = findViewById<Button>(R.id.btnSpellBook)

        // Set buttons to switch pages
        btnDice.setOnClickListener {
            val intent = Intent(this, DiceRollerActivity::class.java)
            startActivity(intent)
        }
        btnSpellBook.setOnClickListener {
            val intent = Intent(this, SpellBook::class.java)
            startActivity(intent)
        }
    }

}
