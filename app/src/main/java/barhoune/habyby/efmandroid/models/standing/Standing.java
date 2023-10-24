package barhoune.habyby.efmandroid.models.standing;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Standing {
    @SerializedName("table")
    @Expose
    private List<Table> table;

    public List<Table> getTable() {
        return table;
    }
}
