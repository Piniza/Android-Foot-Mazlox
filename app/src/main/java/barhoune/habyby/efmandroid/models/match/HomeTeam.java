package barhoune.habyby.efmandroid.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeTeam {
    @SerializedName("tla")
    @Expose
    private String homeTeamName;

    @SerializedName("name")
    @Expose
    private String htName;
    @SerializedName("crest")
    @Expose
    private String homeTeamCrest;

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getHomeTeamCrest() {
        return homeTeamCrest;
    }

    public String getHtName() {
        return htName;
    }
}
