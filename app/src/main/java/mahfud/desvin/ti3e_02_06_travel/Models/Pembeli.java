package mahfud.desvin.ti3e_02_06_travel.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//menyimpan data pembeli
public class Pembeli {
    @SerializedName("id")
            private int id;
    @SerializedName("nama")
            private String nama;
    @SerializedName("alamat")
            private String alamat;
    @SerializedName("telp")
            private String telp;
    @SerializedName("umur")
            private int umur;
    @SerializedName("jeniskelamin")
            private String jeniskelamin;

    private String action;
    public Pembeli(){}
//constructor
    public Pembeli(int id, String nama, String alamat, String telp, int umur, String jeniskelamin, String action){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.umur = umur;
        this.jeniskelamin = jeniskelamin;
    }
//seteer and geteer
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
