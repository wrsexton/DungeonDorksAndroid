package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast

class SpellBook : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

//        val spells = findViewById<TextView>(R.id.textView) //findViewById<RecyclerView>(R.id.lstSpells)
//// ...
//
//// Instantiate the RequestQueue.
//        val queue = Volley.newRequestQueue(this)
//        val url = "http://dnd5eapi.co/api/spells/"
//
//// Request a string response from the provided URL.
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                // Display the first 500 characters of the response string.
//                spells.text = "Response is: ${response.substring(0, 500)}"
//            },
//            Response.ErrorListener {
//                spells.text = it.toString()
//            })
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest)


    }
}
