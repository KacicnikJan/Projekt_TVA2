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

public class ListAdapterVrh extends ArrayAdapter<Vrh> {
    public ListAdapterVrh(Context context, ArrayList<Vrh> vrhArrayList){
        super(context, R.layout.list_item, vrhArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Vrh vrh = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vrh,parent,false);

        }
        TextView vrhIme = convertView.findViewById(R.id.vrhIme);
        TextView visina = convertView.findViewById(R.id.visina);


        vrhIme.setText(vrh.imeVrha);
        visina.setText(vrh.ndmv);



        return convertView;


    }
}
