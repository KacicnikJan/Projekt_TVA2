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

public class ListAdapterHribi extends ArrayAdapter<Hribovje> {
    public ListAdapterHribi(Context context, ArrayList<Hribovje> hribovjeArrayList){
        super(context, R.layout.list_item, hribovjeArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Hribovje hribovje = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        TextView gorovje = convertView.findViewById(R.id.gorovje);
        TextView gorovjeSteviloGor = convertView.findViewById(R.id.gorovjeSteviloGor);

        gorovje.setText(hribovje.ime);



        return convertView;


    }
}
