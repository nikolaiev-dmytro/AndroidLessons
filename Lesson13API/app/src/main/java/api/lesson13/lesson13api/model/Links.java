package api.lesson13.lesson13api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 06.03.2018.
 */

public class Links {

    @SerializedName("link")
    @Expose
    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

}
