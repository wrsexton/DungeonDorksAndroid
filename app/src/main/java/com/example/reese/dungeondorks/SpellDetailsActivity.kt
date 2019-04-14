package com.example.reese.dungeondorks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.reese.dungeondorks.R.styleable.View
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class SpellDetailsActivity : AppCompatActivity() {

    private var spellDetailsIndex: Int = 0

    interface SpellDetailsService {
        @GET
        fun listSpellDetails(@Url url : String): Call<SpellDetails>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_details)
        val spellURL = intent.getStringExtra("SPELL_URL")
        this.initSpellDetails(spellURL)
    }

    private fun initSpellDetails(url : String) {
        // Retrofit boilerplate setup
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("$url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SpellDetailsService::class.java)
        val call = service.listSpellDetails(url)

        val infoText = findViewById<TextView>(R.id.txtTest)
        infoText.text = "Waiting on $url..."

        // Retrofit request API call
        call.enqueue(object : Callback<SpellDetails> {
            override fun onFailure(call: Call<SpellDetails>, t: Throwable) {
                infoText.text = t.toString()
            }

            override fun onResponse(call: Call<SpellDetails>, response: Response<SpellDetails>) {
                if(response.isSuccessful) {
                    val s = response.body()
                    if (s != null) {
                        infoText.text = s.name
                    } else {
                        infoText.text = response.errorBody().toString()
                    }
                } else {
                    infoText.text = response.errorBody().toString()
                }
            }
        })
    }
}
