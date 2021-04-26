package umn.ac.id.luminous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetilActivity extends AppCompatActivity {
    private EditText etNama, etLokasi, etDeskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        etNama = findViewById(R.id.etNama);
        etLokasi = findViewById(R.id.etLokasi);
        etDeskripsi = findViewById(R.id.etDeskripsi);

        Intent intent = getIntent();
        if(intent.hasExtra("MAHASISWA")) {
            Favorites mhs = (Favorites) intent.getSerializableExtra("MAHASISWA");
            etNama.setText(mhs.getNama());
            etLokasi.setText(mhs.getLokasi());
            etDeskripsi.setText(mhs.getDeskripsi());
        } else {

        }
    }

    public void simpanData(View view){

        String mNama = etNama.getText().toString();
        String mLokasi = etLokasi.getText().toString();
        String mDeskripsi = etDeskripsi.getText().toString();

        if(mLokasi.length() <= 0 || mLokasi.length() <= 0 ||
                mDeskripsi.length() <= 0 ){
            Toast.makeText(this,"Semua harus Diisi",
                    Toast.LENGTH_LONG).show();
        } else {
            Intent intentJawab = new Intent();
            Favorites mhs = new Favorites(mNama, mLokasi, mDeskripsi);
            intentJawab.putExtra("MAHASISWA",mhs);
            setResult(RESULT_OK,intentJawab);
            finish();
        }
    }
    public void batal(View view){
        Intent intentJawab = new Intent();
        setResult(RESULT_CANCELED,intentJawab);
        finish();
    }
}