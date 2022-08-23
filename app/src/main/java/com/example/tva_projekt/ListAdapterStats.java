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

public class ListAdapterStats extends ArrayAdapter<Stats> {
    public ListAdapterStats(Context context, ArrayList<Stats> statsArrayList){
        super(context, R.layout.list_view_stats, statsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Stats stats = getItem(position);
        Context context=getContext();
        DBHelper db;
        db=new DBHelper(context);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_stats,parent,false);

        }
        TextView imeHriba = convertView.findViewById(R.id.textViewImeHriba);
        TextView stObiskov = convertView.findViewById(R.id.textViewStObiskov);



        imeHriba.setText(stats.imeVrha);
        stObiskov.setText(stats.stObiskov.toString());



        return convertView;


    }
}