package com.example.notepadbasic;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//Model

public class DataManager {
    SharedPreferences sharedPreferences;
    String SHARED_PREFERENCES_NAME = "MyNotes";

    public DataManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public List<String> getNotes() {
        // Skapar en tom lista för att lagra anteckningarna
        List<String> notes = new ArrayList<>();
        // Hämta alla anteckningar från SharedPreferences
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            notes.add(entry.getValue().toString());
        }
        //Returnerar anteckningarna som en lista
        return notes;
    }

    public void saveNote(String title, String text) {

        // Skapa en redigerare för att ändra SharedPreferences-data
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Hämta ett individullt namn för varje anteckning
        long timestamp = System.currentTimeMillis();
        // Spara den nya anteckningen med det namnet som nyckel och titel + text som värde
        editor.putString(String.valueOf(timestamp), title + "|" + text);
        editor.apply();

    }
    //Tar bort en anteckning med en given titel från SharedPreferences.
    public boolean deleteNoteByTitle(String title) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String entryValue = entry.getValue().toString();
            //Antar att strängen är uppdelat i två, en pipeline som delar titel och text
            String[] noteParts = entryValue.split("\\|");

            if (noteParts.length > 0 && noteParts[0].equals(title)) {
                editor.remove(entry.getKey());
                boolean isRemoved = editor.commit();
                return isRemoved;
            }
        }

        return false;
    }


}
