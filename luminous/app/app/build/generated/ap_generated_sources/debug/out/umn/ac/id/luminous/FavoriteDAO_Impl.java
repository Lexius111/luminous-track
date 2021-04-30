package umn.ac.id.luminous;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class FavoriteDAO_Impl implements FavoriteDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFavorites;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFavorites;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfFavorites;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FavoriteDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavorites = new EntityInsertionAdapter<Favorites>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `tblFavorites`(`nama`,`lokasi`,`deskripsi`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Favorites value) {
        if (value.getNama() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama());
        }
        if (value.getLokasi() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLokasi());
        }
        if (value.getDeskripsi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDeskripsi());
        }
      }
    };
    this.__deletionAdapterOfFavorites = new EntityDeletionOrUpdateAdapter<Favorites>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tblFavorites` WHERE `nama` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Favorites value) {
        if (value.getNama() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama());
        }
      }
    };
    this.__updateAdapterOfFavorites = new EntityDeletionOrUpdateAdapter<Favorites>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tblFavorites` SET `nama` = ?,`lokasi` = ?,`deskripsi` = ? WHERE `nama` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Favorites value) {
        if (value.getNama() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama());
        }
        if (value.getLokasi() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLokasi());
        }
        if (value.getDeskripsi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDeskripsi());
        }
        if (value.getNama() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNama());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tblFavorites";
        return _query;
      }
    };
  }

  @Override
  public void insert(Favorites fav) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavorites.insert(fav);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Favorites fav) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFavorites.handle(fav);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Favorites fav) {
    __db.beginTransaction();
    try {
      __updateAdapterOfFavorites.handle(fav);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Favorites>> getAllFavorite() {
    final String _sql = "SELECT * FROM tblFavorites";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Favorites>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Favorites> compute() {
        if (_observer == null) {
          _observer = new Observer("tblFavorites") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfNama = _cursor.getColumnIndexOrThrow("nama");
          final int _cursorIndexOfLokasi = _cursor.getColumnIndexOrThrow("lokasi");
          final int _cursorIndexOfDeskripsi = _cursor.getColumnIndexOrThrow("deskripsi");
          final List<Favorites> _result = new ArrayList<Favorites>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Favorites _item;
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpLokasi;
            _tmpLokasi = _cursor.getString(_cursorIndexOfLokasi);
            final String _tmpDeskripsi;
            _tmpDeskripsi = _cursor.getString(_cursorIndexOfDeskripsi);
            _item = new Favorites(_tmpNama,_tmpLokasi,_tmpDeskripsi);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
