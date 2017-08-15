package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.MyLocksObj;

import java.util.List;

/**
 * Created by Zheng on 2017/8/14.
 */

public class MyLocksModel extends BaseModel {
    private List<MyLocksObj> object;

    public List<MyLocksObj> getObject() {
        return object;
    }

    public void setObject(List<MyLocksObj> object) {
        this.object = object;
    }
}
