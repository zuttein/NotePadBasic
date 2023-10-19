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

public class PersonalAdapter extends ArrayAdapter<String> {
    private DataManager dataManager;
    private Context context;

    public PersonalAdapter(Context context, List<String> notes, DataManager dataManager) {
        super(context, 0, notes);
        this.dataManager = dataManager;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listaPersonalView = convertView;

        if (listaPersonalView == null) {
            listaPersonalView = LayoutInflater.from(getContext()).inflate(R.layout.personal_adapter, parent, false);
        }

        String currentNote = getItem(position);
        final String[] noteParts = currentNote.split("\\|");

        TextView titleTextView = listaPersonalView.findViewById(R.id.tv_title);

        if (noteParts.length > 0) {
            titleTextView.setText(noteParts[0]);
        }

        final String titleToDelete = noteParts[0];

        listaPersonalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                boolean isRemoved = dataManager.deleteNoteByTitle(titleToDelete);
                if (isRemoved) {

                    remove(currentNote);
                    notifyDataSetChanged();
                }

                return true;
            }
        });


        listaPersonalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hämta den aktuella anteckningen.
                String currentNote = getItem(position);

                // Öppna redigeringsaktiviteten (NewNote) och skicka med anteckningen.
                Intent editIntent = new Intent(context, NewNote.class);
                editIntent.putExtra("note_data", currentNote);
                context.startActivity(editIntent);
            }
        });

        return listaPersonalView;
    }
}









