package omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model;

import com.google.gson.annotations.SerializedName;

import omgimbot.app.sidangapps.features.admin.mhs.model.Mk1;
import omgimbot.app.sidangapps.features.admin.mhs.model.Mk2;
import omgimbot.app.sidangapps.features.admin.mhs.model.Mk3;
import omgimbot.app.sidangapps.features.admin.mhs.model.Mk4;
import omgimbot.app.sidangapps.features.admin.mhs.model.Mk5;
import omgimbot.app.sidangapps.features.admin.mhs.model.Mk6;

public class TracerStudi {
    @SerializedName("nama")
    private String nama;
    @SerializedName("npm")
    private String npm;
    @SerializedName("tempatLahir")
    private String tempatLahir;
    @SerializedName("tanggalLahir")
    private String tanggalLahir;
    @SerializedName("gender")
    private String gender;
    @SerializedName("suku")
    private String suku;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("noHp")
    private String noHp;
    @SerializedName("fak")
    private String fak;
    @SerializedName("prodi")
    private String prodi;
    @SerializedName("tglMasuk")
    private String tglMasuk;
    @SerializedName("prestasi")
    private String prestasi;
    @SerializedName("judulSkripsi")
    private String judulSkripsi;
    @SerializedName("pembimbing1")
    private String pembimbing1;
    @SerializedName("pembimbing2")
    private String pembimbing2;
    @SerializedName("tglLulus")
    private String tglLulus;
    @SerializedName("ipk")
    private String ipk;
    @SerializedName("predikat")
    private String predikat;
    @SerializedName("pekerjaan")
    private String pekerjaan;
    @SerializedName("tglMasukKerja")
    private String tglMasukKerja;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getFak() {
        return fak;
    }

    public void setFak(String fak) {
        this.fak = fak;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getPrestasi() {
        return prestasi;
    }

    public void setPrestasi(String prestasi) {
        this.prestasi = prestasi;
    }

    public String getJudulSkripsi() {
        return judulSkripsi;
    }

    public void setJudulSkripsi(String judulSkripsi) {
        this.judulSkripsi = judulSkripsi;
    }

    public String getPembimbing1() {
        return pembimbing1;
    }

    public void setPembimbing1(String pembimbing1) {
        this.pembimbing1 = pembimbing1;
    }

    public String getPembimbing2() {
        return pembimbing2;
    }

    public void setPembimbing2(String pembimbing2) {
        this.pembimbing2 = pembimbing2;
    }

    public String getTglLulus() {
        return tglLulus;
    }

    public void setTglLulus(String tglLulus) {
        this.tglLulus = tglLulus;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public String getPredikat() {
        return predikat;
    }

    public void setPredikat(String predikat) {
        this.predikat = predikat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getTglMasukKerja() {
        return tglMasukKerja;
    }

    public void setTglMasukKerja(String tglMasukKerja) {
        this.tglMasukKerja = tglMasukKerja;
    }
}
