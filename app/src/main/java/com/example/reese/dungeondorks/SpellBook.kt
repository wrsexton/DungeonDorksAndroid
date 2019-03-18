package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response


interface SpellService {
    @GET("3")
    fun listSpell(): Call<Spell>
}

class SpellBook : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

        val client = OkHttpClient()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://dnd5eapi.co/api/spells/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SpellService::class.java)

        val call = service.listSpell()

        call.enqueue(object : Callback<Spell> {
            override fun onFailure(call: Call<Spell>, t: Throwable) {
                val txt = findViewById<TextView>(R.id.txtTest)
                txt.text = "$t"
            }

            override fun onResponse(call: Call<Spell>, response: Response<Spell>) {
                val txt = findViewById<TextView>(R.id.txtTest)
                if(response.isSuccessful) {
                    val s = response.body()
                    if (s != null) {
                        txt.text = s.name
                    } else {
                        txt.text = "NULL ERR"
                    }
                } else {
                    txt.text = response.errorBody().toString()
                }
            }
        })

    }
}
