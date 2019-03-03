package com.example.reese.dungeondorks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnDiceRoller)

        btn.setOnClickListener {
            val intent = Intent(this, DiceRollerActivity::class.java)
            startActivity(intent)
        }
    }

}
