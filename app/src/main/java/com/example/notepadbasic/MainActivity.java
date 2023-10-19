package com.example.notepadbasic;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;

//Controller
//Viewsen består av xml filer och resources




//TODO
//Göra så att jag kan hoppa in och redigera notes


public class MainActivity extends AppCompatActivity {

    ListView notesList;
    Button notesbtn;
    PersonalAdapter adapter;
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notesList = findViewById(R.id.lv_notes);
        notesbtn = findViewById(R.id.newNote);
        dataManager = new DataManager(this);
        List<String> notes = dataManager.getNotes();

        adapter = new PersonalAdapter(this, notes, dataManager);
        notesList.setAdapter(adapter);




        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);
            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();

        // Uppdatera listan av anteckningar här när jag återgår till main
        List<String> notes = dataManager.getNotes();
        adapter.clear();
        adapter.addAll(notes);
        adapter.notifyDataSetChanged();
    }


}

