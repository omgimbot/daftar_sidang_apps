package omgimbot.app.sidangapps.features.admin.dosen.model;

import com.google.gson.annotations.SerializedName;

public class listMk {
    @SerializedName("kodeMk")
    private String kodeMk;
    @SerializedName("namaMk")
    private String namaMK;

    public String getKodeMk() {return kodeMk;}

    public void setKodeMk(String kodeMk) {this.kodeMk = kodeMk;}

    public String getNamaMK() {return namaMK;}

    public void setNamaMK(String namaMK) {this.namaMK = namaMK;}
}
