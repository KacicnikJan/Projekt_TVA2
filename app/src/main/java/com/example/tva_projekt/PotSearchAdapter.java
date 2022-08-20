package com.example.tva_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;



public class PotSearchAdapter extends ArrayAdapter<Pot> {
    private Context context;
    private int LIMIT = 5;
    private List<Pot> pots;

    public  PotSearchAdapter(Context context, List<Pot> pots){
        super(context,R.layout.pot_search_layout, pots);
        this.context=context;
        this.pots = pots;

    }

    @Override
    public int getCount(){
        return Math.min(LIMIT,pots.size());
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.pot_search_layout,null);
        Pot pot = pots.get(position);
        TextView textViewImePoti = view.findViewById(R.id.textViewImePoti);
        textViewImePoti.setText(pot.getImePoti());
        TextView textViewZahtevnost = view.findViewById(R.id.textViewZahtevnost);
        textViewZahtevnost.setText(pot.getZahtevnost());


        return view;
    }
    @NonNull
    @Override
    public Filter getFilter(){
        return new com.example.tva_projekt.PotSearchAdapter.PotFilter(this, context);
    }
    private class PotFilter extends Filter{

        private com.example.tva_projekt.PotSearchAdapter potSearchAdapter;

        private Context context;
        public PotFilter(com.example.tva_projekt.PotSearchAdapter potSearchAdapter, Context context){
            this.potSearchAdapter=potSearchAdapter;
            this.context=context;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            potSearchAdapter.pots.clear();

            FilterResults filterResults = new FilterResults();
            if(charSequence == null || charSequence.length()== 0){
                filterResults.values = new ArrayList<Pot>();

            }
            else{
                DBHelper dbHelper = new DBHelper(context);
                List<Pot> pots = dbHelper.poisci(charSequence.toString());
                filterResults.values = pots;
                filterResults.count=pots.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            potSearchAdapter.pots.clear();
            potSearchAdapter.pots.addAll((List<Pot>)filterResults.values);
            potSearchAdapter.notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Pot pot = (Pot) resultValue;
            return pot.getImePoti();
        }
    }
}
