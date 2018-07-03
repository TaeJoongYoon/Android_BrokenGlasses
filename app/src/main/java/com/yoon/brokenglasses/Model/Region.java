package com.yoon.brokenglasses.Model;

/**
 * Created by Yoon on 2017-12-27.
 */

public class Region {
    String mainRegion;
    String subRegion;

    public Region(String mainRegion, String subRegion) {
        this.mainRegion = mainRegion;
        this.subRegion = subRegion;
    }

    public String getMainRegion() {
        return mainRegion;
    }
    public void setMainRegion(String mainRegion) {
        this.mainRegion = mainRegion;
    }
    public String getSubRegion() {
        return subRegion;
    }
    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }
}
