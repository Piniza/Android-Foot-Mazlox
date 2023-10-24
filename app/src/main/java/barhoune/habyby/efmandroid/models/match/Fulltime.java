package barhoune.habyby.efmandroid.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fulltime {

    @SerializedName("home")
    @Expose
    public String homescore;

    @SerializedName("away")
    @Expose
    public String awayscore;

    public String getHomescore() {
        return homescore;
    }

    public String getAwayscore() {
        return awayscore;
    }
}
