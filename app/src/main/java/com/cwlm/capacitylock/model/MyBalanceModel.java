package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.MyBalanceObj;

/**
 * Created by Zheng on 2017/8/12.
 */

public class MyBalanceModel extends BaseModel{

    private MyBalanceObj object;

    public MyBalanceObj getObject() {
        return object;
    }

    public void setObject(MyBalanceObj object) {
        this.object = object;
    }
}
