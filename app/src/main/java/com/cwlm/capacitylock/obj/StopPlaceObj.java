package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/8/26.
 */

public class StopPlaceObj {

    /**
     * stopPlaceId : fdssf5645657ghfhtyjhrtytrfyertyery675476465ee5t4w
     * parkAddress : 武汉市江汉区新华路151号
     * name : 纽宾凯国际酒店(新华路)
     * longitude : 114.279016
     * latitude : 30.598631
     * allParkNumber : 1
     * spareParkNumber : 1
     */

    private String stopPlaceId;
    private String parkAddress;
    private String name;
    private String longitude;
    private String latitude;
    private int allParkNumber;
    private int spareParkNumber;

    public String getStopPlaceId() {
        return stopPlaceId;
    }

    public void setStopPlaceId(String stopPlaceId) {
        this.stopPlaceId = stopPlaceId;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getAllParkNumber() {
        return allParkNumber;
    }

    public void setAllParkNumber(int allParkNumber) {
        this.allParkNumber = allParkNumber;
    }

    public int getSpareParkNumber() {
        return spareParkNumber;
    }

    public void setSpareParkNumber(int spareParkNumber) {
        this.spareParkNumber = spareParkNumber;
    }
}
