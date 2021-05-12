package umn.ac.id.luminous;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rv;
    private FavoriteViewModel favVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite);

        rv = (RecyclerView) findViewById(R.id.rvFavorite);

        final FavoriteListAdapter adapter = new FavoriteListAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        favVM = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favVM.getDaftarFavorite().observe(this, new Observer<List<Favorites>>() {
                    @Override
                    public void onChanged(List<Favorites> locs) {
                        adapter.setDaftarFavorites(locs);
                    }
                });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int posisi = viewHolder.getAdapterPosition();
                        Favorites loc = adapter.getFavoriteAtPosition(posisi);
                        if(direction == ItemTouchHelper.RIGHT){
                            Toast.makeText(FavoriteActivity.this, "Favorites dengan nama = "+loc.getNama()+ " dihapus",Toast.LENGTH_LONG).show();
                            favVM.delete(loc);
                        }
                        else if(direction == ItemTouchHelper.LEFT) {
                            Intent editIntet = new Intent(FavoriteActivity.this, DetilActivity.class);
                            editIntet.putExtra("FAVORITES", loc);
                            startActivityForResult(editIntet, REQUEST_EDIT);
                        }
                    }

                }
        );



        helper.attachToRecyclerView(rv);
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Favorites loc = (Favorites)
                    data.getSerializableExtra("FAVORITES");
            if(reqCode == REQUEST_TAMBAH ) {
                favVM.insert(loc);
            }
            else if (reqCode == REQUEST_EDIT) {
                favVM.update(loc);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }



}