package barhoune.habyby.efmandroid.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Match {

    @SerializedName("id")
    @Expose
    private int matchId;
    @SerializedName("utcDate")
    @Expose
    private Date matchDate;

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam matchHomeTeam;

    @SerializedName("awayTeam")
    @Expose
    private AwayTeam matchAwayTeam;

    @SerializedName("score")
    @Expose
    private Score matchScore;

    @SerializedName("stage")
    @Expose
    private String matchStage;

    @SerializedName("venue")
    private String matchVenue;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public String getMatchVenue() {
        return matchVenue;
    }

    public int getMatchId() {
        return matchId;
    }

    public Date getMatchDate() {
        return matchDate;
    }
    public HomeTeam getMatchHomeTeam() {
        return matchHomeTeam;
    }

    public AwayTeam getMatchAwayTeam() {
        return matchAwayTeam;
    }

    public Score getMatchScore() {
        return matchScore;
    }
    public String getMatchStage() {
        return matchStage;
    }
}
