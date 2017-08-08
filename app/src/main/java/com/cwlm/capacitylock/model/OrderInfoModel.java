package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.GetAllStopPlaceObj;
import com.cwlm.capacitylock.obj.OrderInfoObj;

import java.util.List;

/**
 * Created by akawok on 2017-08-08.
 */
public class OrderInfoModel extends BaseModel{


    private List<OrderInfoObj> object;

    public List<OrderInfoObj> getObject() {
        return object;
    }

    public void setObject(List<OrderInfoObj> object) {
        this.object = object;
    }
}
