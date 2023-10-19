package com.example.notepadbasic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewNote extends AppCompatActivity {
    EditText titleInput;
    EditText textInput;
    Button savebtn, cancelbtn;
    SharedPreferences sharedPreferences;
    DataManager dataManager;
    String originalNoteData; // Håll reda på den ursprungliga anteckningen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);

        titleInput = findViewById(R.id.title);
        textInput = findViewById(R.id.text);
        savebtn = findViewById(R.id.save);
        cancelbtn = findViewById(R.id.cancel);

        dataManager = new DataManager(this);
        sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);

        Intent intent = getIntent();
        if (intent.hasExtra("note_data")) {
            originalNoteData = intent.getStringExtra("note_data");
            String[] noteParts = originalNoteData.split("\\|");

            if (noteParts.length == 2) {
                titleInput.setText(noteParts[0]);
                textInput.setText(noteParts[1]);
            }
        }

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                if (title.isEmpty() || text.isEmpty()) {
                    Toast.makeText(NewNote.this, R.string.toast_wrong_input, Toast.LENGTH_SHORT).show();
                } else {
                    if (originalNoteData != null) {
                        // Uppdatera den befintliga anteckningen istället för att skapa en ny
                        dataManager.deleteNoteByTitle(title);
                    }

                    dataManager.saveNote(title, text);
                    Toast.makeText(NewNote.this, "Saved", Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putStringArrayListExtra("updatedNotes", (ArrayList<String>) dataManager.getNotes());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
