package com.example.reese.dungeondorks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Spell {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static Spell parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Spell spellResponse = gson.fromJson(response, Spell.class);
        return spellResponse;
    }

    @Override
    public String toString() {
        return name;
    }
}
