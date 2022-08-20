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

    public class ListAdapterObisk extends ArrayAdapter<Obisk> {
        public ListAdapterObisk(Context context, ArrayList<Obisk> obiskArrayList){
            super(context, R.layout.list_view_obiski, obiskArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            Obisk obisk = getItem(position);
            Context context=getContext();
            DBHelper db;
            db=new DBHelper(context);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_obiski,parent,false);

            }
            TextView imeHriba = convertView.findViewById(R.id.textViewImeHriba);
            TextView imePoti = convertView.findViewById(R.id.textViewImePoti);
            TextView datum = convertView.findViewById(R.id.datum);


            imeHriba.setText(db.pridobiPodatkeVrh(obisk.idObisk));
            datum.setText(obisk.datum);
            imePoti.setText(db.pridobiPodatkePot(obisk.idObisk));


            return convertView;


        }
    }