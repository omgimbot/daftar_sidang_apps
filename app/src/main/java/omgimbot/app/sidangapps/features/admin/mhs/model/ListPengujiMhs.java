package omgimbot.app.sidangapps.features.admin.mhs.model;

import com.google.gson.annotations.SerializedName;

public class ListPengujiMhs {

    @SerializedName("_id")
    private String _id;
    @SerializedName("namaMhs")
    private String namaMhs;
    @SerializedName("nim")
    private String nim;
    @SerializedName("mk1")
    private String mk1;
    @SerializedName("mk2")
    private String mk2;
    @SerializedName("mk3")
    private String mk3;
    @SerializedName("mk4")
    private String mk4;
    @SerializedName("mk5")
    private String mk5;
    @SerializedName("mk6")
    private String mk6;

    @SerializedName("Mk1")
    public Mk1 Mk1;
    @SerializedName("Mk2")
    public Mk2 Mk2;
    @SerializedName("Mk3")
    public Mk3 Mk3;
    @SerializedName("Mk4")
    public Mk4 Mk4;
    @SerializedName("Mk5")
    public Mk5 Mk5;
    @SerializedName("Mk6")
    public Mk6 Mk6;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getMk1() {
        return mk1;
    }

    public void setMk1(omgimbot.app.sidangapps.features.admin.mhs.model.Mk1 mk1) {
        Mk1 = mk1;
    }

    public void setMk1(String mk1) {
        this.mk1 = mk1;
    }

    public String getMk2() {
        return mk2;
    }

    public void setMk2(omgimbot.app.sidangapps.features.admin.mhs.model.Mk2 mk2) {
        Mk2 = mk2;
    }

    public void setMk2(String mk2) {
        this.mk2 = mk2;
    }

    public String getMk3() {
        return mk3;
    }

    public void setMk3(omgimbot.app.sidangapps.features.admin.mhs.model.Mk3 mk3) {
        Mk3 = mk3;
    }

    public void setMk3(String mk3) {
        this.mk3 = mk3;
    }

    public String getMk4() {
        return mk4;
    }

    public void setMk4(omgimbot.app.sidangapps.features.admin.mhs.model.Mk4 mk4) {
        Mk4 = mk4;
    }

    public void setMk4(String mk4) {
        this.mk4 = mk4;
    }

    public String getMk5() {
        return mk5;
    }

    public void setMk5(omgimbot.app.sidangapps.features.admin.mhs.model.Mk5 mk5) {
        Mk5 = mk5;
    }

    public void setMk5(String mk5) {
        this.mk5 = mk5;
    }

    public String getMk6() {
        return mk6;
    }

    public void setMk6(omgimbot.app.sidangapps.features.admin.mhs.model.Mk6 mk6) {
        Mk6 = mk6;
    }

    public void setMk6(String mk6) {
        this.mk6 = mk6;
    }
}
