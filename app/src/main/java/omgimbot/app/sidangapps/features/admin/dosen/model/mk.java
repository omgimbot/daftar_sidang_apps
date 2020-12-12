package omgimbot.app.sidangapps.features.admin.dosen.model;

import com.google.gson.annotations.SerializedName;

public class mk {
    @SerializedName("kodeMk")
    private String kodeMk;
    @SerializedName("namaMk")
    private String namaMk;

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }
}
