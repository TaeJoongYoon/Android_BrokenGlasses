package com.yoon.brokenglasses.Model.network;

import com.yoon.brokenglasses.Model.Region;

import java.util.List;

/**
 * Created by Yoon on 2018-01-15.
 */

public class PersonalPositionPost {
    private String userUID;
    private String birth;
    private int height;
    private int weight;
    private String job;
    private List<String> position;
    private List<Region> region;

    public PersonalPositionPost(String userUID, String birth, int height, int weight, String job, List<String> position, List<Region> region){
        this.userUID = userUID;
        this.birth = birth;
        this.weight = weight;
        this.height = height;
        this.job = job;
        this.position = position;
        this.region = region;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
