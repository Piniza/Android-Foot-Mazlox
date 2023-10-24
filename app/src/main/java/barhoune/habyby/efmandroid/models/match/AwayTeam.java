package barhoune.habyby.efmandroid.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwayTeam {
    @SerializedName("tla")
    @Expose
    private String awayTeamName;

    @SerializedName("name")
    @Expose
    private String atName;

    @SerializedName("crest")
    @Expose
    private String awayTeamCrest;

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getAwayTeamCrest() { return awayTeamCrest; }

    public String getAtName() {
        return atName;
    }
}
