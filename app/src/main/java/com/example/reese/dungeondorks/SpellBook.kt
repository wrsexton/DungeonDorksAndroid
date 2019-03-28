package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response

// Interface for API calls to spells from 5e API
interface SpellService {
    @GET("spells/")
    fun listSpellAllSpells(): Call<Spells>
}

class SpellBook : AppCompatActivity() {

    // Full list of all spells
    private lateinit var spellsList: List<Spell>
    // Recycler View List of spells for user to search through
    private lateinit var recyclerView: RecyclerView
    // Adapter for Recycler View
    private lateinit var viewAdapter: SpellbookRecyclerAdapter
    // Layout Manager for Recycler View
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

        // Used for preventing the keyboard from displacing the list of spells on the screen
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        // failsafe init to avoid crash
        spellsList = listOf()

        // Grab all spells from API
        initSpellsList()
        // Init Recycler View
        initRecyclerView()
        // Set up search functionality
        initSearchFunctionality()

    }

    private fun initSearchFunctionality() {
        // Grab search input field and set link its onTextChanged event to get spells filtered by input text.
        val editSpellSearch = findViewById<EditText>(R.id.editSpellSearch)
        editSpellSearch.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getFilteredSpells(s.toString())
            }

            // Unused
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = SpellbookRecyclerAdapter(mutableListOf())

        recyclerView = findViewById<RecyclerView>(R.id.lstSpells).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    private fun initSpellsList() {
        // Retrofit boilerplate setup
        val apiBaseURL = "http://dnd5eapi.co/api/"
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(apiBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SpellService::class.java)
        val call = service.listSpellAllSpells()

        val editSpellSearch = findViewById<EditText>(R.id.editSpellSearch)
        val infoText = findViewById<TextView>(R.id.txtInfo)
        infoText.visibility = View.VISIBLE
        infoText.text = "Waiting on response from http://dnd5eapi.co/api/..."
        editSpellSearch.isEnabled = false

        // Retrofit request API call
        call.enqueue(object : Callback<Spells> {
            override fun onFailure(call: Call<Spells>, t: Throwable) {
                infoText.text = t.toString()
            }

            override fun onResponse(call: Call<Spells>, response: Response<Spells>) {
                if(response.isSuccessful) {
                    val s = response.body()
                    if (s != null) {
                        infoText.visibility = View.INVISIBLE
                        editSpellSearch.isEnabled = true
                        spellsList = s.results.toList()
                    } else {
                        infoText.visibility = View.VISIBLE
                        infoText.text = response.errorBody().toString()
                    }
                } else {
                    infoText.visibility = View.VISIBLE
                    infoText.text = response.errorBody().toString()
                }
            }
        })
    }

    fun getFilteredSpells(search : String) {
        if(search.isNullOrEmpty()) {
            viewAdapter.setData(mutableListOf())
            return
        }
        viewAdapter.setData(getSpellsByName(search))
    }

    fun getSpellsByName(search : String) : MutableList<Spell> {
        var spells = mutableListOf<Spell>()
        for(s in spellsList) if(s.name.contains(search,true)) spells.add(s)
        return spells
    }
}
