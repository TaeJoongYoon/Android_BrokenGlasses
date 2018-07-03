package com.yoon.brokenglasses.Model.network;

import com.yoon.brokenglasses.Model.Region;

import java.util.List;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamSearchResponse {
    private boolean error;
    private String teamUID;
    private String teamName;
    private String teamBirth;
    private String captainUID;
    private List<String> formation;
    private List<Region> region;

    public TeamSearchResponse(boolean error, String teamUID, String teamName, String teamBirth, String captainUID, List<String> formation, List<Region> region){
        this.error = error;
        this.teamUID = teamUID;
        this.teamName = teamName;
        this.teamBirth = teamBirth;
        this.captainUID = captainUID;
        this.formation = formation;
        this.region = region;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        this.teamUID = teamUID;
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

    public List<String> getFormation() {
        return formation;
    }

    public void setFormation(List<String> formation) {
        this.formation = formation;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }
}
