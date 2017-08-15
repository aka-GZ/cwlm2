package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.BindParkObj;

import java.util.List;

/**
 * Created by Zheng on 2017/6/14.
 * 目前被GetAllStopPlaceModel替代
 */

public class BindParkModel extends BaseModel{

    private List<BindParkObj> object;


    public List<BindParkObj> getObject() {
        return object;
    }

    public void setObject(List<BindParkObj> object) {
        this.object = object;
    }



}
