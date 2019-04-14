package com.example.reese.dungeondorks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class SpellDetails {
    private String _id;
    private int index;
    private String name;
    private List<String> desc;

    public String get_id() { return _id; }
    public int getIndex() { return index; }
    public String getName() {
        return name;
    }
    public List<String> getDesc() { return desc; }

    public static SpellDetails parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        SpellDetails spellDetailsResponse = gson.fromJson(response, SpellDetails.class);
        return spellDetailsResponse;
    }

    @Override
    public String toString() {
        return name;
    }
}