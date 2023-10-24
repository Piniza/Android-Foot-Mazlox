package barhoune.habyby.efmandroid.models.team;

import com.google.gson.annotations.SerializedName;

public class Coachs {
    @SerializedName("name")
    public String Coachname;
    @SerializedName("nationality")
    public String nationality;

    public String getCoachname() {
        return Coachname;
    }

    public String getNationality() {
        return nationality;
    }
}
