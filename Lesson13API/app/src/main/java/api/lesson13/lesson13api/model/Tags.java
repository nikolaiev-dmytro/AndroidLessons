package api.lesson13.lesson13api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 06.03.2018.
 */

class Tags {

    @SerializedName("tag")
    @Expose
    private List<Tag> tag = null;

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

}
