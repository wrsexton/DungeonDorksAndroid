package com.example.reese.dungeondorks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Spell {
//    private int _id;
    private int index;
    private String name;
//    private String[] desc;

//    public int get_id() {
//        return _id;
//    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

//    public String[] getDesc() {
//        return desc;
//    }

    public static Spell parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Spell spellResponse = gson.fromJson(response, Spell.class);
        return spellResponse;
    }
}
