package com.example.notepadbasic;

import static com.example.notepadbasic.DataManager.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//Controller för views och att hämta in employees och skickar in i listviewn

public class PersonalAdapter extends ArrayAdapter<Notes> {
    public PersonalAdapter(Context context, ArrayList<Notes> note) {
        super(context, 0, notes);}

    @NonNull
    public View getView (int posistion, View convertView, @NonNull ViewGroup parent) {


        View listaPersonalView = convertView;

        //Inflatar min nya adapter i Listviewn

        if(listaPersonalView == null){
            listaPersonalView = LayoutInflater.from(getContext()).inflate(R.layout.personal_adapter, parent, false);
        }
        // Hämtar den aktuella anställda från listan
        Notes currentNotes = getItem(posistion);

        //Sätter id på samtliga element
        TextView titleTextview = listaPersonalView.findViewById(R.id.tv_title);

        //Lägger in användarens inmatning i Listviewn
        titleTextview.setText(currentNotes.getTitle());


        return listaPersonalView;


    }


}
