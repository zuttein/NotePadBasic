package com.example.notepadbasic;

import java.util.ArrayList;

public class DataManager {
    // En ArrayList som används för att lagra Notes-objekt.
    public static ArrayList<Notes> notes = new ArrayList<>();

    // Lägger till en ny anteckning i listan
    public Notes createNote(String title, String text) {
        Notes notess = new Notes(title,text);
        notes.add(notess);
        return notess;
    }
}


