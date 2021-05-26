package umn.ac.id.luminous;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Favorites.class}, version = 2, exportSchema = false)
public abstract class FavoriteRoomDatabase extends RoomDatabase {
    public abstract FavoriteDAO daoFavorite();

    private static FavoriteRoomDatabase INSTANCE;

    static FavoriteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FavoriteRoomDatabase.class, "dbFavorite")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
