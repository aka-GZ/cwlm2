package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.VipStateObj;

import java.util.List;

/**
 * Created by Zheng on 2017/6/17.
 */

public class VipStateModel extends BaseModel{


    /**
     * statusCode : 1
     * mess : 获取充值记录成功
     * object : [{"monthCardId":"1","userId":"1","stopPlaceId":"1","createTime":"2017-06-20 12:12:12","startTime":"2017-06-20 12:12:12","deadLine":"2017-07-20 12:12:12","payMoney":1}]
     */

    private List<VipStateObj> object;

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

    public List<VipStateObj> getObject() {
        return object;
    }

    public void setObject(List<VipStateObj> object) {
        this.object = object;
    }
}
