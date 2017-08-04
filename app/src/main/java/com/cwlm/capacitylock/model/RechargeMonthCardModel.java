package com.cwlm.capacitylock.model;

/**
 * Created by Zheng on 2017/6/17.
 */

public class RechargeMonthCardModel extends BaseModel{


    /**
     * statusCode : 1
     * mess : 获取成功
     * object : {"stopPlaceId":"1","quarterPrice":3,"halfYearPrice":6,"yearPrice":12}
     */

    private ObjectBean object;

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

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * stopPlaceId : 1
         * quarterPrice : 3.0
         * halfYearPrice : 6.0
         * yearPrice : 12.0
         */

        private String stopPlaceId;
        private double monthPrice;
        private double quarterPrice;
        private double halfYearPrice;
        private double yearPrice;

        public double getMonthPrice() {
            return monthPrice;
        }

        public void setMonthPrice(double monthPrice) {
            this.monthPrice = monthPrice;
        }

        public String getStopPlaceId() {
            return stopPlaceId;
        }

        public void setStopPlaceId(String stopPlaceId) {
            this.stopPlaceId = stopPlaceId;
        }

        public double getQuarterPrice() {
            return quarterPrice;
        }

        public void setQuarterPrice(double quarterPrice) {
            this.quarterPrice = quarterPrice;
        }

        public double getHalfYearPrice() {
            return halfYearPrice;
        }

        public void setHalfYearPrice(double halfYearPrice) {
            this.halfYearPrice = halfYearPrice;
        }

        public double getYearPrice() {
            return yearPrice;
        }

        public void setYearPrice(double yearPrice) {
            this.yearPrice = yearPrice;
        }
    }
}
