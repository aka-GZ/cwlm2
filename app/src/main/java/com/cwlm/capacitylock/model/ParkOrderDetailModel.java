package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.CarlocksObj;
import com.cwlm.capacitylock.obj.StopPlaceObj;

import java.util.List;

/**
 * Created by Zheng on 2017/8/26.
 * 预订车位list
 */

public class ParkOrderDetailModel extends BaseModel{


    /**
     * map : {"stopPlace":{"stopPlaceId":"fdssf5645657ghfhtyjhrtytrfyertyery675476465ee5t4w","parkAddress":"武汉市江汉区新华路151号","name":"纽宾凯国际酒店(新华路)","longitude":"114.279016","latitude":"30.598631","allParkNumber":1,"spareParkNumber":1},"carlocks":[{"carLockId":"1g56s4g65s1gr15g6s5rg1s5grs1rgs32g1s651rs32g65s1rgs651rgs","routerId":9,"addr":1,"state":0,"upordown":1,"predetermine":0,"manGrade":5,"managerId":"0","stopPlaceId":"fdssf5645657ghfhtyjhrtytrfyertyery675476465ee5t4w","longitude":"0","latitude":"0","parkNumber":"D0102","isPri":0,"floor":"-5"}]}
     */

    private MapBean map;

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * stopPlace : {"stopPlaceId":"fdssf5645657ghfhtyjhrtytrfyertyery675476465ee5t4w","parkAddress":"武汉市江汉区新华路151号","name":"纽宾凯国际酒店(新华路)","longitude":"114.279016","latitude":"30.598631","allParkNumber":1,"spareParkNumber":1}
         * carlocks : [{"carLockId":"1g56s4g65s1gr15g6s5rg1s5grs1rgs32g1s651rs32g65s1rgs651rgs","routerId":9,"addr":1,"state":0,"upordown":1,"predetermine":0,"manGrade":5,"managerId":"0","stopPlaceId":"fdssf5645657ghfhtyjhrtytrfyertyery675476465ee5t4w","longitude":"0","latitude":"0","parkNumber":"D0102","isPri":0,"floor":"-5"}]
         */

        private StopPlaceObj stopPlace;
        private List<CarlocksObj> carlocks;

        public StopPlaceObj getStopPlace() {
            return stopPlace;
        }

        public void setStopPlace(StopPlaceObj stopPlace) {
            this.stopPlace = stopPlace;
        }

        public List<CarlocksObj> getCarlocks() {
            return carlocks;
        }

        public void setCarlocks(List<CarlocksObj> carlocks) {
            this.carlocks = carlocks;
        }
    }
}
