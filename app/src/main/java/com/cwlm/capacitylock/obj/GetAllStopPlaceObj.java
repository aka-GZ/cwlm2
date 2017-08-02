package com.cwlm.capacitylock.obj;

/**
 * Created by akawok on 2017-07-29.
 */
public class GetAllStopPlaceObj {

    /**
     * stopPlaceId : 14981164500509913092
     * parkAddress : 武汉市东湖高新区高新二路19号红桃K电商云工场
     * name : 红桃K
     * longitude : 114.4251
     * latitude : 30.484468
     * allParkNumber : 1
     * spareParkNumber : 1
     */

    private String stopPlaceId;
    private String parkAddress;
    private String name;
    private String longitude;
    private String latitude;
    private String stopPrice;
    private String allParkNumber;
    private String spareParkNumber;


    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

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
