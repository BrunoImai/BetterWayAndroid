package com.example.betterwayfinal.Activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterwayfinal.R;
import com.example.betterwayfinal.Activity.model.Recycler;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private List<Recycler> listaCord;

    public RecyclerAdapter(List<Recycler> lista) {
        this.listaCord = lista;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                                                .inflate(R.layout.lista_recycler, parent, false);

        return new MyviewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Recycler recycler = listaCord.get(position);
        String cordxS =  Double.toString(recycler.getCordX());
        String cordyS =  Double.toString(recycler.getCordY());
        String cordzS =  Double.toString(recycler.getCordZ());
        String latS =  Double.toString(recycler.getLatitude());
        String lonS =  Double.toString(recycler.getLongitude());

        holder.cordx.setText(cordxS);
        holder.cordy.setText(cordyS);
        holder.cordz.setText(cordzS);
        holder.lat.setText(latS);
        holder.lon.setText(lonS);

    }

    @Override
    public int getItemCount() {
        return this.listaCord.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView cordx;
        TextView cordy;
        TextView cordz;
        TextView lat;
        TextView lon;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            cordx = itemView.findViewById(R.id.CoordenadaX);
            cordy = itemView.findViewById(R.id.CoordenadaY);
            cordz = itemView.findViewById(R.id.CoordenadaZ);
            lat = itemView.findViewById(R.id.Latitude);
            lon = itemView.findViewById(R.id.Longitude);
        }
    }

}
