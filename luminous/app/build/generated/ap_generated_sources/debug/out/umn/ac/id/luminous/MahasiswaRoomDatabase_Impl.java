package umn.ac.id.luminous;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class MahasiswaRoomDatabase_Impl extends MahasiswaRoomDatabase {
  private volatile MahasiswaDAO _mahasiswaDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tblMahasiswa` (`nim` TEXT NOT NULL, `nama` TEXT, `email` TEXT, `nomorhp` TEXT, PRIMARY KEY(`nim`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5ea190ffcbfd35bab99a04fb52ac9ccd\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `tblMahasiswa`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTblMahasiswa = new HashMap<String, TableInfo.Column>(4);
        _columnsTblMahasiswa.put("nim", new TableInfo.Column("nim", "TEXT", true, 1));
        _columnsTblMahasiswa.put("nama", new TableInfo.Column("nama", "TEXT", false, 0));
        _columnsTblMahasiswa.put("email", new TableInfo.Column("email", "TEXT", false, 0));
        _columnsTblMahasiswa.put("nomorhp", new TableInfo.Column("nomorhp", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTblMahasiswa = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTblMahasiswa = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTblMahasiswa = new TableInfo("tblMahasiswa", _columnsTblMahasiswa, _foreignKeysTblMahasiswa, _indicesTblMahasiswa);
        final TableInfo _existingTblMahasiswa = TableInfo.read(_db, "tblMahasiswa");
        if (! _infoTblMahasiswa.equals(_existingTblMahasiswa)) {
          throw new IllegalStateException("Migration didn't properly handle tblMahasiswa(umn.ac.id.luminous.Mahasiswa).\n"
                  + " Expected:\n" + _infoTblMahasiswa + "\n"
                  + " Found:\n" + _existingTblMahasiswa);
        }
      }
    }, "5ea190ffcbfd35bab99a04fb52ac9ccd", "26be727435a3756565609e9b5551577d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "tblMahasiswa");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tblMahasiswa`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MahasiswaDAO daoMahasiswa() {
    if (_mahasiswaDAO != null) {
      return _mahasiswaDAO;
    } else {
      synchronized(this) {
        if(_mahasiswaDAO == null) {
          _mahasiswaDAO = new MahasiswaDAO_Impl(this);
        }
        return _mahasiswaDAO;
      }
    }
  }
}
