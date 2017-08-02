package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.GetAllStopPlaceObj;

import java.util.List;

/**
 * Created by akawok on 2017-07-29.
 */
public class GetAllStopPlaceModel extends BaseModel{


    private List<GetAllStopPlaceObj> object;

    public List<GetAllStopPlaceObj> getObject() {
        return object;
    }

    public void setObject(List<GetAllStopPlaceObj> object) {
        this.object = object;
    }

}
