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
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(FavoriteActivity.this, DetilActivity.class);
                startActivityForResult(addIntent,REQUEST_TAMBAH);
            }
        });
        rv = (RecyclerView) findViewById(R.id.rvMahasiswa);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mhsVM = ViewModelProviders.of(this).get(MahasiswaViewModel.class);
        mhsVM.getDaftarMahasiswa().observe(this,
                new Observer<List<Favorites>>() {
                    @Override
                    public void onChanged(List<Favorites> mhss) {
                        adapter.setDaftarFavorites(mhss);
                    }
                });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int posisi = viewHolder.getAdapterPosition();
                        Favorites mhs = adapter.getMahasiswaAtPosition(posisi);
                        if(direction == ItemTouchHelper.LEFT){
                            Toast.makeText(FavoriteActivity.this,
                                    "Favorites dengan NIM = "+mhs.getNama()+
                                            " dihapus",Toast.LENGTH_LONG).show();
                            mhsVM.delete(mhs);
                        }
                        else if(direction == ItemTouchHelper.RIGHT) {
                            Intent editIntet =
                                    new Intent(FavoriteActivity.this,
                                            DetilActivity.class);
                            editIntet.putExtra("MAHASISWA", mhs);
                            startActivityForResult(editIntet,
                                    REQUEST_EDIT);
                        }
                    }
                }
        );



        helper.attachToRecyclerView(rv);
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode,
                                 Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Favorites mhs = (Favorites)
                    data.getSerializableExtra("MAHASISWA");
            if(reqCode == REQUEST_TAMBAH ) {
                mhsVM.insert(mhs);
            }
            else if (reqCode == REQUEST_EDIT) {
                mhsVM.update(mhs);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }



}