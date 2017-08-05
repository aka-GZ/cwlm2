package com.cwlm.capacitylock.model;

/**
 * Created by Zheng on 2017/6/14.
 */

public class RechargeModel extends BaseModel{


    /**
     * statusCode : 1
     * mess : 完整的符合支付宝参数规范的订单信息
     * map : {"money":"5","payInfo":"partner=\"2088021739597205\"&seller_id=\"zylm_net@163.com\"&out_trade_no=\"061415322917926\"&subject=\"车位\"&body=\"地锁租用费用\"&total_fee=\"5\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"&sign=\"LmaQDjxzmAGIi%2BqpwJFmhi%2BSs9ssP5N8dXdPozI3yFWhnyeM%2BkEVRf2LJh5%2FrxLp17Ours84i1M85%2BnZaYmxwQwydhu3kSrELJ04eOM840s6SyArwCIP9KOtfWR2LczlfdhwyvzQfbtUpiVmUtUvgE7ZBSgt5EZKufsojM283NA%3D\"&sign_type=\"RSA\""}
     */

    private MapBean map;

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

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * money : 5
         * payInfo : partner="2088021739597205"&seller_id="zylm_net@163.com"&out_trade_no="061415322917926"&subject="车位"&body="地锁租用费用"&total_fee="5"&notify_url="http://notify.msp.hk/notify.htm"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="LmaQDjxzmAGIi%2BqpwJFmhi%2BSs9ssP5N8dXdPozI3yFWhnyeM%2BkEVRf2LJh5%2FrxLp17Ours84i1M85%2BnZaYmxwQwydhu3kSrELJ04eOM840s6SyArwCIP9KOtfWR2LczlfdhwyvzQfbtUpiVmUtUvgE7ZBSgt5EZKufsojM283NA%3D"&sign_type="RSA"
         */

        private String money;
        private String payInfo;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }
}
