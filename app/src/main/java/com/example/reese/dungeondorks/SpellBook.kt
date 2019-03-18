package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response


interface SpellService {
    @GET("spells/")
    fun listSpellAllSpells(): Call<Spells>
}

class SpellBook : AppCompatActivity() {

    lateinit var spellsList: Array<Spell>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

        initSpellsList()

        val editSpellSearch = findViewById<EditText>(R.id.editSpellSearch)
        editSpellSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("not implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getAllSpells(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //TODO("not implemented")
            }
        })
    }

    fun initSpellsList() {
        // Retrofit boilerplate setup
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://dnd5eapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SpellService::class.java)
        val call = service.listSpellAllSpells()

        // Retrofit request call
        call.enqueue(object : Callback<Spells> {
            override fun onFailure(call: Call<Spells>, t: Throwable) {
                val txt = findViewById<TextView>(R.id.txtTest)
                txt.text = "$t"
            }

            override fun onResponse(call: Call<Spells>, response: Response<Spells>) {
                if(response.isSuccessful) {
                    val s = response.body()
                    if (s != null) {
                        spellsList = s.results
                    } else {
                        //txt.text = "NULL ERR"
                    }
                } else {
                    //txt.text = response.errorBody().toString()
                }
            }
        })
    }

    fun getAllSpells(search : String) {
        val searchAsInt = search.toIntOrNull() ?: return
        val txt = findViewById<TextView>(R.id.txtTest)
        txt.text = spellsList[searchAsInt].name
    }
}
