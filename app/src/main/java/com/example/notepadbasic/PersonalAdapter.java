package com.example.notepadbasic;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PersonalAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> notes;
    private DataManager dataManager;

    public PersonalAdapter(Context context, List<String> notes, DataManager dataManager) {
        super(context, 0, notes);
        this.context = context;
        this.notes = notes;
        this.dataManager = dataManager;
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



        return listaPersonalView;
    }




}
