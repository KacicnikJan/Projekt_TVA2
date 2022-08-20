package com.example.tva_projekt;

import android.content.Context;
import android.content.Intent;
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
        super(context, R.layout.list_item_vrh, vrhArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Context context=getContext();
        DBHelper db;
        db=new DBHelper(context);
        Vrh vrh = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vrh,parent,false);

        }
        TextView vrhIme = convertView.findViewById(R.id.vrhIme);
        TextView visina = convertView.findViewById(R.id.visina);
        if(vrh.ndmv > 2000){
            convertView.findViewById(R.id.slika).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.slika_hrib).setVisibility(View.GONE);
        }else {
            convertView.findViewById(R.id.slika).setVisibility(View.GONE);
            convertView.findViewById(R.id.slika_hrib).setVisibility(View.VISIBLE);
        }

        vrhIme.setText(vrh.imeVrha);
        visina.setText(vrh.ndmv.toString());

        return convertView;
    }
}
