package com.example.notepadbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText titleInput;
    EditText textInput;


    DataManager dataManager = new DataManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sätter id på samtliga element

        titleInput = findViewById(R.id.title);
        textInput = findViewById(R.id.text);
        Button savebtn = findViewById(R.id.save);
        Button notesbtn = findViewById(R.id.yournotes);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                //Felhantering om användaren inte fyller i korrekt

                if (title.isEmpty() || text.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.toast_wrong_input, Toast.LENGTH_SHORT).show();

                    //Skapar ny employee i datamana gern, samt skriver ut att detta är gjort
                } else {

                    Notes n = dataManager.createNote(title, text);
                    Toast.makeText(MainActivity.this, R.string.toast_registrered, Toast.LENGTH_SHORT).show();
                }
            }

        });

        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Går till ny Intent som visar anteckningarna igen

                Intent intent = new Intent(MainActivity.this, NotesGathered.class);
                startActivity(intent);




            }
        });






    }
}