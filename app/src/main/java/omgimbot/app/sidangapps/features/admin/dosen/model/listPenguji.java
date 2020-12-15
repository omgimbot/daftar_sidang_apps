package omgimbot.app.sidangapps.features.admin.dosen.model;

import com.google.gson.annotations.SerializedName;

public class listPenguji {
    @SerializedName("_id")
    private String _id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("kodeMk")
    private String kodeMk;
    @SerializedName("mk")
    public mk mk;

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

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

    public omgimbot.app.sidangapps.features.admin.dosen.model.mk getMk() {
        return mk;
    }

    public void setMk(omgimbot.app.sidangapps.features.admin.dosen.model.mk mk) {
        this.mk = mk;
    }

}
