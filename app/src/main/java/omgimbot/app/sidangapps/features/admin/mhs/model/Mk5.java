package omgimbot.app.sidangapps.features.admin.mhs.model;

import com.google.gson.annotations.SerializedName;

public class Mk5 {
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("kodeMk")
    private String KodeMk;

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

    public String getKodeMk() {
        return KodeMk;
    }

    public void setKodeMk(String kodeMk) {
        KodeMk = kodeMk;
    }
}
