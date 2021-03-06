package com.yoon.brokenglasses.Model.network;

import com.yoon.brokenglasses.Model.Region;

import java.util.List;

/**
 * Created by Yoon on 2018-01-30.
 */

public class TeamPositionPost {
    private String teamName;
    private String teamBirth;
    private String captainUID;
    private List<Region> region;
    private List<String> formation;

    public TeamPositionPost(String teamName, String teamBirth, String captainUID, List<Region> region, List<String> formation){
        this.teamName = teamName;
        this.teamBirth = teamBirth;
        this.captainUID = captainUID;
        this.region = region;
        this.formation = formation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamBirth() {
        return teamBirth;
    }

    public void setTeamBirth(String teamBirth) {
        this.teamBirth = teamBirth;
    }

    public String getCaptainUID() {
        return captainUID;
    }

    public void setCaptainUID(String captainUID) {
        this.captainUID = captainUID;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }

    public List<String> getFormation() {
        return formation;
    }

    public void setFormation(List<String> formation) {
        this.formation = formation;
    }
}
