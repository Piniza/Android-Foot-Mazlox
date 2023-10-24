package barhoune.habyby.efmandroid.models.team;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("id")
    private int ID;
    @SerializedName("name")
    private String name;
    @SerializedName("shortname")
    private String shortname;
    @SerializedName("tla")
    private String tla;
    @SerializedName("crest")
    private String crestUrl;

    @SerializedName("website")
    private String web;

    @SerializedName("founded")
    private String annee;
    @SerializedName("address")
    private String address;
    @SerializedName("clubColors")
    private String couleurs;
    @SerializedName("venue")
    private String Tiran;
    @SerializedName("coach")
    private Coachs coachs;

    public Coachs getCoachs() { return coachs;
    }

    public String getAnnee() {
        return annee;
    }
    public String getWeb() {
        return web;
    }
    public String getName() {
        return name;
    }
    public String getCrestUrl() {
        return crestUrl;
    }
    public int getID() {
        return ID;
    }

    public String getAddress() {
        return address;
    }

    public String getCouleurs() {
        return couleurs;
    }

    public String getTiran() {
        return Tiran;
    }

    public String getShortname() {
        return shortname;
    }

    public String getTla() {
        return tla;
    }
}

