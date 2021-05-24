package umn.ac.id.luminous;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tblFavorites")
public class Favorites implements Serializable {
    @ColumnInfo(name = "nama")
    private String nama;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lokasi")
    private String lokasi;
    @ColumnInfo(name = "deskripsi")
    private String deskripsi;

    public Favorites(String nama, String lokasi, String deskripsi){
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    public void setNama(String nama) {this.nama = nama; }
    public void setLokasi(String lokasi) {this.lokasi = lokasi; }
    public void setDeskripsi(String deskripsi) {this.deskripsi = deskripsi;}

    public String getNama() { return this.nama; }
    public String getLokasi() { return this.lokasi; }
    public String getDeskripsi() { return this.deskripsi;}

}
