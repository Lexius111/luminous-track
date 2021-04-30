package umn.ac.id.luminous;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class FavoriteRepository {
    private FavoriteDAO daoFavorite;
    private LiveData<List<Favorites>> daftarFavorite;

    FavoriteRepository(Application app){
        FavoriteRoomDatabase db =
                FavoriteRoomDatabase.getDatabase(app);
        daoFavorite = db.daoFavorite();
        daftarFavorite = daoFavorite.getAllFavorite();
    }
    LiveData<List<Favorites>> getDaftarFavorite(){
        return this.daftarFavorite;
    }
    public void insert(Favorites loc){
        new FavoriteRepository.insertAsyncTask(daoFavorite).execute(loc);
    }
    public void deleteAll(){
        new FavoriteRepository.deleteAllAsyncTask(daoFavorite).execute();
    }
    public void delete(Favorites loc) {
        new FavoriteRepository.deleteAsyncTask(daoFavorite).execute(loc);
    }
    public void update(Favorites loc) {
        new FavoriteRepository.updateAsyncTask(daoFavorite).execute(loc);
    }
    private static class insertAsyncTask extends
            AsyncTask<Favorites, Void, Void> {
        private FavoriteDAO asyncDaoFavorite;
        insertAsyncTask(FavoriteDAO dao){
            this.asyncDaoFavorite = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoFavorite.insert(favorites[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends
            AsyncTask<Void, Void, Void> {
        private FavoriteDAO asyncDaoFavorite;
        deleteAllAsyncTask(FavoriteDAO dao){
            this.asyncDaoFavorite = dao;
        }
        @Override
        protected Void doInBackground(final Void... voids) {
            asyncDaoFavorite.deleteAll();
            return null;
        }
    }
    private static class deleteAsyncTask extends
            AsyncTask<Favorites, Void, Void> {
        private FavoriteDAO asyncDaoFavorite;
        deleteAsyncTask(FavoriteDAO dao){
            this.asyncDaoFavorite = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoFavorite.delete(favorites[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends
            AsyncTask<Favorites, Void, Void> {
        private FavoriteDAO asyncDaoFavorite;
        updateAsyncTask(FavoriteDAO dao){
            this.asyncDaoFavorite = dao;
        }
        @Override
        protected Void doInBackground(final Favorites... favorites) {
            asyncDaoFavorite.update(favorites[0]);
            return null;
        }
    }
}
