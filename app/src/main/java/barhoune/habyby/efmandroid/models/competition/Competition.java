package barhoune.habyby.efmandroid.models.competition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Competition {
        @SerializedName("id")
        @Expose
        private int competitionId;

        public String getCompetitionCode() {
            return competitionCode;
        }

        @SerializedName("code")
        @Expose
        private String competitionCode;

        @SerializedName("name")
        @Expose
        private String competitionName;

        @SerializedName("emblem")
        private String crestUrl;

        public String getCrestUrl() {
            return crestUrl;
        }

        public int getCompetitionId() {
            return competitionId;
        }

        public String getCompetitionName() {
            return competitionName;
        }
}
