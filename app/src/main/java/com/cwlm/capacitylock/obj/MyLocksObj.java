package com.cwlm.capacitylock.obj;


/**
 * Created by akawok on 2017-08-14.
 */
public class MyLocksObj{

private String stopPlaceName;//  "金地京汉1903",
private String upordown;//    0占用 1空闲
private String routerId;//   车位总控号
private String addr;//      编号
private String parkNumber;//  "C024"车位锁号


    public String getStopPlaceName() {
        return stopPlaceName;
    }

    public void setStopPlaceName(String stopPlaceName) {
        this.stopPlaceName = stopPlaceName;
    }

    public String getUpordown() {
        return upordown;
    }

    public void setUpordown(String upordown) {
        this.upordown = upordown;
    }

    public String getRouterId() {
        return routerId;
    }

    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }
}
