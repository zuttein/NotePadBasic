package com.example.notepadbasic;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class NewNote extends AppCompatActivity {
    EditText titleInput;
    EditText textInput;
    Button savebtn;


    DataManager dataManager = new DataManager();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);

        titleInput = findViewById(R.id.title);
        textInput = findViewById(R.id.text);
        savebtn = findViewById(R.id.save);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                //Felhantering om användaren inte fyller i korrekt

                if (title.isEmpty() || text.isEmpty()) {
                    Toast.makeText(NewNote.this, R.string.toast_wrong_input, Toast.LENGTH_SHORT).show();

                    //Skapar ny employee i datamana gern, samt skriver ut att detta är gjort
                } else {

                    Notes n = dataManager.createNote(title, text);
                    Toast.makeText(NewNote.this, R.string.toast_registrered, Toast.LENGTH_SHORT).show();
                }
            }

        });







    }
}