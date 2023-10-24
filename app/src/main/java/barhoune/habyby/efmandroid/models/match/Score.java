package barhoune.habyby.efmandroid.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("fullTime")
    @Expose
    public Fulltime fulltime;

    public Fulltime getFulltime() {
        return fulltime;
    }
}
