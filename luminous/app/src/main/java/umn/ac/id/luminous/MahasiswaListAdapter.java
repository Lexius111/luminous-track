package umn.ac.id.luminous;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private final LayoutInflater mInflater;
    private List<Favorites> daftarFavorites;
    MahasiswaListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.msh_item_layout, parent,false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        if(daftarFavorites != null){
            Favorites mhs = daftarFavorites.get(position);
            holder.tvNama.setText("Nama: " + mhs.getNama());
            holder.tvLokasi.setText("Lokasi: " + mhs.getLokasi());
            holder.tvDeskripsi.setText("Deskripsi: "+ mhs.getDeskripsi());
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
    public Favorites getMahasiswaAtPosition(int posisi){
        return daftarFavorites.get(posisi);
    }
    void setDaftarFavorites(List<Favorites> mhs){
        daftarFavorites = mhs;
        notifyDataSetChanged();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNama;
        private final TextView tvLokasi;
        private final TextView tvDeskripsi;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvItemNama);
            tvLokasi = itemView.findViewById(R.id.tvItemLokasi);
            tvDeskripsi = itemView.findViewById(R.id.tvItemDeskripsi);

        }
    }



}
