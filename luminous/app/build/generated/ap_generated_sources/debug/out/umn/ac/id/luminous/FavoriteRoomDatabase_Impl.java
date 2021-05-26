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
public final class FavoriteRoomDatabase_Impl extends FavoriteRoomDatabase {
  private volatile FavoriteDAO _favoriteDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tblFavorites` (`nama` TEXT NOT NULL, `lokasi` TEXT, `deskripsi` TEXT, PRIMARY KEY(`nama`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"ca2a4356204a91c3f6d468019883594c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `tblFavorites`");
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
        final HashMap<String, TableInfo.Column> _columnsTblFavorites = new HashMap<String, TableInfo.Column>(3);
        _columnsTblFavorites.put("nama", new TableInfo.Column("nama", "TEXT", true, 1));
        _columnsTblFavorites.put("lokasi", new TableInfo.Column("lokasi", "TEXT", false, 0));
        _columnsTblFavorites.put("deskripsi", new TableInfo.Column("deskripsi", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTblFavorites = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTblFavorites = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTblFavorites = new TableInfo("tblFavorites", _columnsTblFavorites, _foreignKeysTblFavorites, _indicesTblFavorites);
        final TableInfo _existingTblFavorites = TableInfo.read(_db, "tblFavorites");
        if (! _infoTblFavorites.equals(_existingTblFavorites)) {
          throw new IllegalStateException("Migration didn't properly handle tblFavorites(umn.ac.id.luminous.Favorites).\n"
                  + " Expected:\n" + _infoTblFavorites + "\n"
                  + " Found:\n" + _existingTblFavorites);
        }
      }
    }, "ca2a4356204a91c3f6d468019883594c", "f1e0d7800540cad89438399771510371");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "tblFavorites");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tblFavorites`");
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
  public FavoriteDAO daoFavorite() {
    if (_favoriteDAO != null) {
      return _favoriteDAO;
    } else {
      synchronized(this) {
        if(_favoriteDAO == null) {
          _favoriteDAO = new FavoriteDAO_Impl(this);
        }
        return _favoriteDAO;
      }
    }
  }
}
