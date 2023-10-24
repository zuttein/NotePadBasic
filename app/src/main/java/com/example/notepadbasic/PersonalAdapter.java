package com.example.notepadbasic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;


//Controller

public class PersonalAdapter extends ArrayAdapter<String> {
    DataManager dataManager;
    Context context;

    public PersonalAdapter(Context context, List<String> notes, DataManager dataManager) {
        super(context, 0, notes);
        this.dataManager = dataManager;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listaNoteView = convertView;

        if (listaNoteView == null) {
            listaNoteView = LayoutInflater.from(getContext()).inflate(R.layout.personal_adapter, parent, false);
        }

        String currentNote = getItem(position);

        String[] noteParts = currentNote.split("\\|");

        TextView titleTextView = listaNoteView.findViewById(R.id.tv_title);

        //Visa endast title
        titleTextView.setText(noteParts[0]);

        // Extrahera titeln som ska tas bort
        String titleToDelete = noteParts[0];

        listaNoteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Ta bort anteckningen med hjälp av metoden i datamanagern
                dataManager.deleteNoteByTitle(titleToDelete);

                // Ta bort den aktuella anteckningen från adaptern och uppdatera listan
                remove(currentNote);
                notifyDataSetChanged();


                return false;
            }
        });


        listaNoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hämta den aktuella anteckningen.
                String currentNote = getItem(position);

                // Öppna redigeringsaktiviteten (NewNote) och skicka med anteckningen.
                Intent editIntent = new Intent(context, NewNoteActivity.class);
                editIntent.putExtra("note_data", currentNote);
                context.startActivity(editIntent);
            }
        });

        return listaNoteView;

    }



}










