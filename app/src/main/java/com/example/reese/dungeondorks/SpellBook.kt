package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    private lateinit var spellsList: List<Spell>
    private lateinit var spellsListDisplayed: MutableList<Spell>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SpellbookRecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

        // Grab all spells from API
        initSpellsList()
        // Init Recycler View
        initRecyclerView()
        // Set up search functionality
        initSearchFunctionality()

    }

    private fun initSearchFunctionality() {
        val editSpellSearch = findViewById<EditText>(R.id.editSpellSearch)
        editSpellSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("not implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //val txt = findViewById<TextView>(R.id.txtTest)
                getFilteredSpells(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                //TODO("not implemented")
            }
        })
    }

    private fun initRecyclerView() {
        spellsListDisplayed = mutableListOf()

        viewManager = LinearLayoutManager(this)
        viewAdapter = SpellbookRecyclerAdapter(spellsListDisplayed)

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
                        spellsList = s.results.toList()
                    } else {
                        //txt.text = "NULL ERR"
                    }
                } else {
                    //txt.text = response.errorBody().toString()
                }
            }
        })
    }

    fun getFilteredSpells(search : String) {
        spellsListDisplayed.clear()
        if(search.isNullOrEmpty()) {
            return
        }
        //val txt = findViewById<TextView>(R.id.txtTest)
        //txt.text = getSpellsByName(search).toString()
//        getSpellsByName(search).forEach {
//            spellsListDisplayed.add(it)
//        }
        viewAdapter.setData(getSpellsByName(search))

        //var strSpells = spells.toString()
        //return spells
    }

    fun getSpellsByName(search : String) : MutableList<Spell> {
        var spells = mutableListOf<Spell>()

        for(s in spellsList) if(s.name.contains(search,true)) spells.add(s)
        return spells
    }
}
