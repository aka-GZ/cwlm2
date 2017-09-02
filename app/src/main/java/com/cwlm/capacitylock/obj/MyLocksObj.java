package com.cwlm.capacitylock.obj;


/**
 * Created by akawok on 2017-08-14.
 */
public class MyLocksObj {

    private String carLockId; // 车位锁id
    private String stopPlaceName;//  "金地京汉1903",
    private String state; //是否共享状态 0共享 1表示未共享
    private String upordown;//    0占用 1空闲
    private String routerId;//   车位总控号
    private String addr;//      编号
    private String carNumber; //鄂A12345"//使用者车牌号
    private String parkNumber;//  "C024"车位锁号
    private String useTime; //2017-07-07 16:58:55"//使用时间
    private String endHourTime;  //分享截止时间


    public String getEndHourTime() {
        return endHourTime;
    }

    public void setEndHourTime(String endHourTime) {
        this.endHourTime = endHourTime;
    }

    public String getCarLockId() {
        return carLockId;
    }

    public void setCarLockId(String carLockId) {
        this.carLockId = carLockId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

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
