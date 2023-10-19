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
        List<String> notes = new ArrayList<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            notes.add(entry.getValue().toString());
        }
        return notes;
    }

    public void saveNote(String title, String text) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        long timestamp = System.currentTimeMillis();
        editor.putString(String.valueOf(timestamp), title + "|" + text);
        editor.apply();

    }




}
