package omgimbot.app.sidangapps.features.mhs.model;

import com.google.gson.annotations.SerializedName;

public class daftarModel {
    @SerializedName("key")
    private String key;
    @SerializedName("nama")
    private String nama;

    @SerializedName("nim")
    private String nim;

    @SerializedName("judul")
    private String judul;

    @SerializedName("file")
    private String file;

    @SerializedName("filename")
    private String filename;

    @SerializedName("dosen")
    private String dosen;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("status")
    private String status;

    @SerializedName("surattugas")
    private String surattugas;

    public String getSurattugas() {
        return surattugas;
    }

    public void setSurattugas(String surattugas) {
        this.surattugas = surattugas;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
