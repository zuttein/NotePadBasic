package com.example.notepadbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sätter id på samtliga element
        notesList = findViewById(R.id.lv_notes);
        Button notesbtn = findViewById(R.id.newNote);





        // Skapar en anpassad adapter för att visa listan över anställda
        PersonalAdapter adapter = new PersonalAdapter(this, DataManager.notes);

        adapter.notifyDataSetChanged();

        // Kopplar adaptern till listview för att visa anställda i lista
        notesList.setAdapter(adapter);

        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);




            }
        });










    }
}
