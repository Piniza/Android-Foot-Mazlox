package barhoune.habyby.efmandroid.models.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import barhoune.habyby.efmandroid.models.competition.Competition;
import barhoune.habyby.efmandroid.models.match.Match;

public class Standings {
    @SerializedName("count")
    @Expose
    private Integer countStanding;

    @SerializedName("standings")
    @Expose
    private List<Standing> standingList;

    @SerializedName("competition")
    @Expose
    private Competition competition;

    public Integer getCountStanding() {
        return countStanding;
    }

    public List<Standing> getStandingList() {
        return standingList;
    }

    public Competition getCompetition() {
        return competition;
    }
}
