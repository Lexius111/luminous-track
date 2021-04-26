package umn.ac.id.luminous;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private MahasiswaRepository mhsRepository;
    private LiveData<List<Favorites>> daftarMahasiswa;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        mhsRepository = new MahasiswaRepository(application);
        daftarMahasiswa = mhsRepository.getDaftarMahasiswa();
    }
    LiveData<List<Favorites>> getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }
    public void insert(Favorites mhs) {
        mhsRepository.insert(mhs);
    }
    public void deleteAll() {
        mhsRepository.deleteAll();
    }
    public void delete(Favorites mhs) {
        mhsRepository.delete(mhs);
    }
    public void update(Favorites mhs) {
        mhsRepository.update(mhs);
    }
}
