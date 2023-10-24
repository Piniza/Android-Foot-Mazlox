package barhoune.habyby.efmandroid.models.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teams {
    @SerializedName("count")
    @Expose
    private Integer countTeams;

    @SerializedName("teams")
    @Expose
    private List<Team> teamList;

    public Integer getCountTeams() {
        return countTeams;
    }

    public List<Team> getTeamsList() {
        return teamList;
    }
}
