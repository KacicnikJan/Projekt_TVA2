package com.example.tva_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class ListAdapterPot extends ArrayAdapter<Pot> {
    public ListAdapterPot(Context context, ArrayList<Pot> potArrayList){
        super(context, R.layout.list_item_pot, potArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Pot pot = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_pot,parent,false);

        }
        TextView potIme = convertView.findViewById(R.id.potIme);
        TextView zahtevnost = convertView.findViewById(R.id.zahtevnost);
        TextView casHoje = convertView.findViewById(R.id.casHoje);



        potIme.setText(pot.potIme);
        zahtevnost.setText(pot.zahtevnost);
        casHoje.setText(pot.casHoje);



        return convertView;


    }
}
