package com.cwlm.capacitylock.obj;

/**
 * Created by Zheng on 2017/8/12.
 */

public class MyBalanceObj {


    /**
     * unionWeixinUserId : null
     * mpAccountId : null
     * userId : a0b04e80-ae2f-4b96-bfc6-030b9bb56f3b1487741750095
     * openId : null
     * accountBalance : 0.0
     * deposit : null
     * create_time : null
     * carNumber : null
     * plate_type : null
     * isDelete : null
     * state : null
     */

    private String unionWeixinUserId;
    private String mpAccountId;
    private String userId;
    private String openId;
    private double accountBalance;
    private String deposit;
    private String create_time;
    private String carNumber;
    private String plate_type;
    private String isDelete;
    private String state;

    public String getUnionWeixinUserId() {
        return unionWeixinUserId;
    }

    public void setUnionWeixinUserId(String unionWeixinUserId) {
        this.unionWeixinUserId = unionWeixinUserId;
    }

    public String getMpAccountId() {
        return mpAccountId;
    }

    public void setMpAccountId(String mpAccountId) {
        this.mpAccountId = mpAccountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPlate_type() {
        return plate_type;
    }

    public void setPlate_type(String plate_type) {
        this.plate_type = plate_type;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
