package com.nikolaiev.lesson27retrofitrx;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("woeid")
    @Expose
    private Integer woeid;
    @SerializedName("latt_long")
    @Expose
    private String lattLong;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getLattLong() {
        return lattLong;
    }

    public void setLattLong(String lattLong) {
        this.lattLong = lattLong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (title != null ? !title.equals(location.title) : location.title != null) return false;
        if (locationType != null ? !locationType.equals(location.locationType) : location.locationType != null)
            return false;
        if (woeid != null ? !woeid.equals(location.woeid) : location.woeid != null) return false;
        return lattLong != null ? lattLong.equals(location.lattLong) : location.lattLong == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (locationType != null ? locationType.hashCode() : 0);
        result = 31 * result + (woeid != null ? woeid.hashCode() : 0);
        result = 31 * result + (lattLong != null ? lattLong.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "title='" + title + '\'' +
                ", locationType='" + locationType + '\'' +
                ", woeid=" + woeid +
                ", lattLong='" + lattLong + '\'' +
                '}';
    }
}
