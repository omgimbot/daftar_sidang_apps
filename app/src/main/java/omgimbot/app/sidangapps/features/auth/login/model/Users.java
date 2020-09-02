package omgimbot.app.sidangapps.features.auth.login.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("nama")
    private String nama;
    @SerializedName("username")
    private String username;

    @SerializedName("role")
    private String role;

    @SerializedName("password")
    private String password;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("fakultas")
    private String fakultas;

    @SerializedName("prodi")
    private String prodi;

    @SerializedName("dosen")
    private String dosen;

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
