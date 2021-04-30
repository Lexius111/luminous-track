package umn.ac.id.luminous;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {
    private final LayoutInflater mInflater;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final int REQUEST_CODE = 101;
    private List<Favorites> daftarFavorites;
    FavoriteListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public FavoriteListAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.msh_item_layout, parent,false);
        return new FavoriteListAdapter.FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        if(daftarFavorites != null){
            Favorites loc = daftarFavorites.get(position);
            holder.tvNama.setText("Nama: " + loc.getNama());
            holder.tvLokasi.setText("Lokasi: " + loc.getLokasi());
            holder.tvDeskripsi.setText("Deskripsi: "+ loc.getDeskripsi());
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if(daftarFavorites != null){
            return daftarFavorites.size();
        } else {
            return 0;
        }
    }
    public Favorites getFavoriteAtPosition(int posisi){
        return daftarFavorites.get(posisi);
    }
    void setDaftarFavorites(List<Favorites> loc){
        daftarFavorites = loc;
        notifyDataSetChanged();;
    }


    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNama;
        private final TextView tvLokasi;
        private final TextView tvDeskripsi;


        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvItemNama);
            tvLokasi = itemView.findViewById(R.id.tvItemLokasi);
            tvDeskripsi = itemView.findViewById(R.id.tvItemDeskripsi);
        }

    }



}

