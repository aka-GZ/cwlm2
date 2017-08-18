package com.cwlm.capacitylock.obj;

/**
 * Created by akawok on 2017-08-17.
 */
public class PayObj {


    /**
     * statusCode : 1
     * money : 10
     * mess : 充值成功
     * park : 红桃k停车场
     * date : 3小时56分
     */

    private String statusCode;
    private String money;
    private String mess;
    private String park;
    private String date;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
