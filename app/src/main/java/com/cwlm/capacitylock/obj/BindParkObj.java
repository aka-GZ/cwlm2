package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/8/4.
 */

public class BindParkObj {

    /**
     * stopPlaceId : 停车场Id
     * name : 停车场名
     * parkAddress : 停车场地址
     * allParkNumber : 总共停车位
     * spareParkNumber : 空闲车位
     */

    private String stopPlaceId;
    private String name;
    private String parkAddress;
    private String allParkNumber;
    private String spareParkNumber;

    public String getStopPlaceId() {
        return stopPlaceId;
    }

    public void setStopPlaceId(String stopPlaceId) {
        this.stopPlaceId = stopPlaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public String getAllParkNumber() {
        return allParkNumber;
    }

    public void setAllParkNumber(String allParkNumber) {
        this.allParkNumber = allParkNumber;
    }

    public String getSpareParkNumber() {
        return spareParkNumber;
    }

    public void setSpareParkNumber(String spareParkNumber) {
        this.spareParkNumber = spareParkNumber;
    }
}
