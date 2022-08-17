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

public class VrhSearchAdapter extends ArrayAdapter<Vrh> {
    private Context context;
    private int LIMIT = 5;
    private List<Vrh> vrhs;

    public  VrhSearchAdapter(Context context, List<Vrh> vrhs){
        super(context,R.layout.vrh_search_layout, vrhs);
        this.context=context;
        this.vrhs = vrhs;

    }

    @Override
    public int getCount(){
        return Math.min(LIMIT,vrhs.size());
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.vrh_search_layout,null);
        Vrh vrh = vrhs.get(position);
        TextView textViewImeHriba = view.findViewById(R.id.textViewImeHriba);
        textViewImeHriba.setText(vrh.getImeVrha());
        TextView textViewVisina = view.findViewById(R.id.textViewVisinaHriba);
        textViewVisina.setText(vrh.getNdmv().toString());

        return view;
    }
    @NonNull
    @Override
    public Filter getFilter(){
        return new VrhFilter(this, context);
    }
    private class VrhFilter extends Filter{

        private VrhSearchAdapter vrhSearchAdapter;

        private Context context;
        public VrhFilter(VrhSearchAdapter vrhSearchAdapter, Context context){
            this.vrhSearchAdapter=vrhSearchAdapter;
            this.context=context;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            vrhSearchAdapter.vrhs.clear();

            FilterResults filterResults = new FilterResults();
            if(charSequence == null || charSequence.length()== 0){
                filterResults.values = new ArrayList<Vrh>();
                //filterResults.count=vrhs.size();
            }
            else{
                DBHelper dbHelper = new DBHelper(context);
                List<Vrh> vrhs = dbHelper.search(charSequence.toString());
                filterResults.values = vrhs;
                filterResults.count=vrhs.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            vrhSearchAdapter.vrhs.clear();
            vrhSearchAdapter.vrhs.addAll((List<Vrh>)filterResults.values);
            vrhSearchAdapter.notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Vrh vrh = (Vrh) resultValue;
            return vrh.getImeVrha();
        }
    }
}
