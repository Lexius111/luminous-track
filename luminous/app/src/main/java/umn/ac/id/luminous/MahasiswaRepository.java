package umn.ac.id.luminous;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaRepository {
    private MahasiswaDAO daoMahasiswa;
    private LiveData<List<Favorites>> daftarMahasiswa;

    MahasiswaRepository(Application app){
        MahasiswaRoomDatabase db =
                MahasiswaRoomDatabase.getDatabase(app);
        daoMahasiswa = db.daoMahasiswa();
        daftarMahasiswa = daoMahasiswa.getAllMahasiswa();
    }
    LiveData<List<Favorites>> getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }
    public void insert(Favorites mhs){
        new insertAsyncTask(daoMahasiswa).execute(mhs);
    }
    public void deleteAll(){
        new deleteAllAsyncTask(daoMahasiswa).execute();
    }
    public void delete(Favorites mhs) {
        new deleteAsyncTask(daoMahasiswa).execute(mhs);
    }
    public void update(Favorites mhs) {
        new updateAsyncTask(daoMahasiswa).execute(mhs);
    }
    private static class insertAsyncTask extends
            AsyncTask<Favorites, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        insertAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoMahasiswa.insert(favorites[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends
            AsyncTask<Void, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAllAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Void... voids) {
            asyncDaoMahasiswa.deleteAll();
            return null;
        }
    }
    private static class deleteAsyncTask extends
            AsyncTask<Favorites, Void, Void>{
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoMahasiswa.delete(favorites[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends
            AsyncTask<Favorites, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        updateAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoMahasiswa.update(favorites[0]);
            return null;
        }
    }
}
