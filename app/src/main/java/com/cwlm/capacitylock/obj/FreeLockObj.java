package com.cwlm.capacitylock.obj;

import java.util.List;

/**
 * Created by akawok on 2017-05-20.
 */
public class FreeLockObj {

    private String statusCode;
    private String mess;// 错误信息

    private List<CarLockObj> object;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public List<CarLockObj> getObject() {
        return object;
    }

    public void setObject(List<CarLockObj> object) {
        this.object = object;
    }
}
