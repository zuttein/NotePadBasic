package com.example.notepadbasic;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

//Controller för Listviewn och knapp
public class NotesGathered extends AppCompatActivity {

    ListView notesList;
    Button backbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_gathered);

        //Sätter id på samtliga element
        notesList = findViewById(R.id.lv_notes);
        backbutton = findViewById(R.id.btn_back);



        // Skapar en anpassad adapter för att visa listan över anställda
        PersonalAdapter adapter = new PersonalAdapter(this, DataManager.notes);

        adapter.notifyDataSetChanged();

        // Kopplar adaptern till listview för att visa anställda i lista
        notesList.setAdapter(adapter);


        backbutton.setOnClickListener(new View.OnClickListener() {

            //Tar användaren tillbaka till första aktiviteten
            @Override
            public void onClick(View view) {
                finish();

            }
        });






    }
}