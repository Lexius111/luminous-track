package umn.ac.id.luminous;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private FavoriteRepository favRepository;
    private LiveData<List<Favorites>> DaftarFavorite;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favRepository = new FavoriteRepository(application);
        DaftarFavorite = favRepository.getDaftarFavorite();
    }
    LiveData<List<Favorites>> getDaftarFavorite(){
        return this.DaftarFavorite;
    }
    public void insert(Favorites fav) {
        favRepository.insert(fav);
    }
    public void deleteAll() {
        favRepository.deleteAll();
    }
    public void delete(Favorites fav) { favRepository.delete(fav); }
    public void update(Favorites fav) { favRepository.update(fav);
    }
}
