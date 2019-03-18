package com.example.reese.dungeondorks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Spells {
    private int count;
    private Spell[] results;

    public int getCount() {
        return count;
    }

    public Spell[] getResults() {
        return results;
    }

    public static Spells parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Spells spellsResponse = gson.fromJson(response, Spells.class);
        return spellsResponse;
    }
}
