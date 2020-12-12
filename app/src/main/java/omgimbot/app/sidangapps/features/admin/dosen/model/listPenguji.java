package omgimbot.app.sidangapps.features.admin.dosen.model;

import com.google.gson.annotations.SerializedName;

public class listPenguji {
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("kodeMk")
    private String kodeMk;
    @SerializedName("mk")
    public mk mk;

    public omgimbot.app.sidangapps.features.admin.dosen.model.mk getMk() {
        return mk;
    }

    public void setMk(omgimbot.app.sidangapps.features.admin.dosen.model.mk mk) {
        this.mk = mk;
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

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

}
