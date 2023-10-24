package barhoune.habyby.efmandroid.models.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import barhoune.habyby.efmandroid.models.team.Team;

public class Table {
    @SerializedName("position")
    @Expose
    public String rank;
    @SerializedName("team")
    @Expose
    public Team team;
    @SerializedName("playedGames")
    @Expose
    public String playedGames;
    @SerializedName("won")
    @Expose
    public String wins;
    @SerializedName("draw")
    @Expose
    public String draw;
    @SerializedName("lost")
    @Expose
    public String lost;
    @SerializedName("points")
    @Expose
    public String rankpoints;

    public String getRank() {
        return rank;
    }

    public String getWins() {
        return wins;
    }

    public String getDraw() {
        return draw;
    }

    public String getLost() {
        return lost;
    }

    public String getRankpoints() {
        return rankpoints;
    }

    public Team getTeam() {
        return team;
    }

    public String getPlayedGames() {
        return playedGames;
    }


}
