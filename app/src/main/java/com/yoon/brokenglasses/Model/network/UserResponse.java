package com.yoon.brokenglasses.Model.network;

import com.yoon.brokenglasses.Model.Region;

import java.util.List;

/**
 * Created by Yoon on 2018-01-24.
 */

public class UserResponse {
    private boolean error;
    private String userUID;
    private String userName;
    private String job;
    private String birth;
    private int height;
    private int weight;
    private List<String> position;
    private List<Region> region;

    public UserResponse(boolean error, String userUID, String userName, String job, String birth, int height, int weight, List<String> position, List<Region> region) {
        this.error = error;
        this.userUID = userUID;
        this.userName = userName;
        this.job = job;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.region = region;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }
}
