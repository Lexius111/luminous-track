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
public interface MahasiswaDAO {
    @Query("SELECT * FROM tblFavorites")
    LiveData<List<Favorites>> getAllMahasiswa();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favorites mhs);

    @Delete
    void delete(Favorites mhs);

    @Update
    void update(Favorites mhs);

    @Query("DELETE FROM tblFavorites")
    void deleteAll();
}