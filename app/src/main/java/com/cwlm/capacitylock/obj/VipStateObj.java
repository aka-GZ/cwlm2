package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/8/5.
 */

public class VipStateObj {
    /**
     * monthCardId : 1
     * userId : 1
     * stopPlaceId : 1
     * createTime : 2017-06-20 12:12:12
     * startTime : 2017-06-20 12:12:12
     * deadLine : 2017-07-20 12:12:12
     * payMoney : 1.0
     */

    private String monthCardId;
    private String userId;
    private String stopPlaceId;
    private String stopPlaceName;
    private String createTime;
    private String startTime;
    private String deadLine;
    private double payMoney;


    public String getStopPlaceName() {
        return stopPlaceName;
    }

    public void setStopPlaceName(String stopPlaceName) {
        this.stopPlaceName = stopPlaceName;
    }

    public String getMonthCardId() {
        return monthCardId;
    }

    public void setMonthCardId(String monthCardId) {
        this.monthCardId = monthCardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStopPlaceId() {
        return stopPlaceId;
    }

    public void setStopPlaceId(String stopPlaceId) {
        this.stopPlaceId = stopPlaceId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }
}
