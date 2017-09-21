package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/9/6.
 */

public class GetStopPlaceInfoObj {


    /**
     * stopPlaceId : 15042580660227817549
     * parkAddress : 湖北省武汉市洪山区文治街与文昌路的交汇处，武汉理工大学新校区南大门正对面
     * name : 武昌府二期
     * addTime : 2017-09-01 17:27:46.0
     * updateTime : 2017-09-01 17:27:46
     * longitude : 114.335825
     * latitude : 30.50857
     * isDelete : false
     * img : null
     * stopPlaceManagerId : null
     * stopPrice : 3.0
     * versionName : null
     * addTimeStamp : 1504258068000
     * allParkNumber : 100
     * spareParkNumber : 20
     * accountBalance : null
     * distance : null
     * phoneNum : null
     */

    private String stopPlaceId;
    private String parkAddress;
    private String name;
    private String addTime;
    private String updateTime;
    private String longitude;
    private String latitude;
    private boolean isDelete;
    private Object img;
    private Object stopPlaceManagerId;
    private String stopPrice;
    private Object versionName;
    private long addTimeStamp;
    private String allParkNumber;
    private String spareParkNumber;
    private Object accountBalance;
    private Object distance;
    private Object phoneNum;

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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public Object getStopPlaceManagerId() {
        return stopPlaceManagerId;
    }

    public void setStopPlaceManagerId(Object stopPlaceManagerId) {
        this.stopPlaceManagerId = stopPlaceManagerId;
    }


    public Object getVersionName() {
        return versionName;
    }

    public void setVersionName(Object versionName) {
        this.versionName = versionName;
    }

    public long getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(long addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
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

    public Object getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Object accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public Object getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Object phoneNum) {
        this.phoneNum = phoneNum;
    }
}
