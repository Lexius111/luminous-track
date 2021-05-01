package umn.ac.id.luminous;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Query("SELECT * FROM tblFavorites")
    LiveData<List<Favorites>> getAllFavorite();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favorites fav);

    @Delete
    void delete(Favorites fav);

    @Update
    void update(Favorites fav);

    @Query("DELETE FROM tblFavorites")
    void deleteAll();
}

