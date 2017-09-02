package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/9/1.
 */

public class PredetermineObj {


    /**
     * parkAddress : 武汉市江岸区汉口京汉大道大智路金地京汉1903A座
     * stopPlaceName : 金地京汉1903
     * floor : -1
     * longitude : 114.291006
     * latitude : 30.590335
     * orderInfoId : 0f588042-7307-4f7e-982d-79df71a12852
     * createTime : 2017-02-16 10:21:23
     * routerId : 2
     * addr : 37
     * parkNumber : C021
     */

    private String parkAddress;
    private String stopPlaceName;
    private String floor;
    private String longitude;
    private String latitude;
    private String orderInfoId;
    private String createTime;
    private int routerId;
    private int addr;
    private String parkNumber;
    private String endHourTime;  //分享截止时间


    public String getEndHourTime() {
        return endHourTime;
    }

    public void setEndHourTime(String endHourTime) {
        this.endHourTime = endHourTime;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public String getStopPlaceName() {
        return stopPlaceName;
    }

    public void setStopPlaceName(String stopPlaceName) {
        this.stopPlaceName = stopPlaceName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public String getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(String orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getRouterId() {
        return routerId;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }
}
