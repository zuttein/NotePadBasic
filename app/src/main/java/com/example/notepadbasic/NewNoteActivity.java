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
//Controller
public class NewNoteActivity extends AppCompatActivity {
    EditText titleInput;
    EditText textInput;
    Button saveBtn, cancelBtn;
    SharedPreferences sharedPreferences;
    DataManager dataManager;
    String originalNoteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);

        titleInput = findViewById(R.id.title);
        textInput = findViewById(R.id.text);
        saveBtn = findViewById(R.id.save);
        cancelBtn = findViewById(R.id.cancel);

        dataManager = new DataManager(this);
        sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);

        Intent intent = getIntent();
        //Hanterar om aktiviteten har content redan, dvs redigering
        if (intent.hasExtra("note_data")) {
            originalNoteData = intent.getStringExtra("note_data");
            String[] noteParts = originalNoteData.split("\\|");

            if (noteParts.length == 2) {
                titleInput.setText(noteParts[0]);
                textInput.setText(noteParts[1]);
            }
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                if (Title.isEmpty() || text.isEmpty()) {
                    Toast.makeText(NewNoteActivity.this, R.string.toast_wrong_input, Toast.LENGTH_SHORT).show();
                } else {
                    if (originalNoteData != null) {
                        // Hämta den gamla titeln och texten
                        String[] noteParts = originalNoteData.split("\\|");
                        String oldTitle = noteParts[0];

                        // Ta bort den befintliga anteckningen med den gamla titeln
                        dataManager.deleteNoteByTitle(oldTitle);

                        // Spara den befintliga anteckningen med den nya titeln och texten
                        dataManager.saveNote(Title, text);
                    } else {
                        // Om det inte fanns någon ursprunglig data, skapa en ny anteckning
                        dataManager.saveNote(Title, text);
                    }

                    Toast.makeText(NewNoteActivity.this, "Saved", Toast.LENGTH_SHORT).show();


                    // Skapa en ny Intent med uppdaterade anteckningar och resultatkoden
                    Intent resultIntent = new Intent();
                    resultIntent.putStringArrayListExtra("updatedNotes", (ArrayList<String>) dataManager.getNotes());

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Avslutar den nuvarande aktiviteten
                finish();
            }
        });
    }
}
