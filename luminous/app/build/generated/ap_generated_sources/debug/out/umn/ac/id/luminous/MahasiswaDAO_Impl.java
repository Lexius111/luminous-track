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
public final class MahasiswaDAO_Impl implements MahasiswaDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMahasiswa;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMahasiswa;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMahasiswa;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MahasiswaDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMahasiswa = new EntityInsertionAdapter<Mahasiswa>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `tblMahasiswa`(`nim`,`nama`,`email`,`nomorhp`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Mahasiswa value) {
        if (value.getNim() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNim());
        }
        if (value.getNama() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNama());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmail());
        }
        if (value.getNomorhp() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNomorhp());
        }
      }
    };
    this.__deletionAdapterOfMahasiswa = new EntityDeletionOrUpdateAdapter<Mahasiswa>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tblMahasiswa` WHERE `nim` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Mahasiswa value) {
        if (value.getNim() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNim());
        }
      }
    };
    this.__updateAdapterOfMahasiswa = new EntityDeletionOrUpdateAdapter<Mahasiswa>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tblMahasiswa` SET `nim` = ?,`nama` = ?,`email` = ?,`nomorhp` = ? WHERE `nim` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Mahasiswa value) {
        if (value.getNim() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNim());
        }
        if (value.getNama() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNama());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmail());
        }
        if (value.getNomorhp() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNomorhp());
        }
        if (value.getNim() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNim());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tblMahasiswa";
        return _query;
      }
    };
  }

  @Override
  public void insert(Mahasiswa mhs) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMahasiswa.insert(mhs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Mahasiswa mhs) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMahasiswa.handle(mhs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Mahasiswa mhs) {
    __db.beginTransaction();
    try {
      __updateAdapterOfMahasiswa.handle(mhs);
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
  public LiveData<List<Mahasiswa>> getAllMahasiswa() {
    final String _sql = "SELECT * FROM tblMahasiswa";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Mahasiswa>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Mahasiswa> compute() {
        if (_observer == null) {
          _observer = new Observer("tblMahasiswa") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfNim = _cursor.getColumnIndexOrThrow("nim");
          final int _cursorIndexOfNama = _cursor.getColumnIndexOrThrow("nama");
          final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
          final int _cursorIndexOfNomorhp = _cursor.getColumnIndexOrThrow("nomorhp");
          final List<Mahasiswa> _result = new ArrayList<Mahasiswa>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Mahasiswa _item;
            final String _tmpNim;
            _tmpNim = _cursor.getString(_cursorIndexOfNim);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpNomorhp;
            _tmpNomorhp = _cursor.getString(_cursorIndexOfNomorhp);
            _item = new Mahasiswa(_tmpNim,_tmpNama,_tmpEmail,_tmpNomorhp);
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
