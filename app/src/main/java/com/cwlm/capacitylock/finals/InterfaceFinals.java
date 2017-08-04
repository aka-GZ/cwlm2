package com.cwlm.capacitylock.finals;

import android.os.Environment;

import java.io.File;

/**
 * Created by akawok on 2017-07-28.
 */
public class InterfaceFinals {

    public static final String URL = "http://zylm.net:80"; //正式服务器
//    public static final String URL = "http://wooowoo.cn";  //测试服务器

    //请求URL
    public static final String getAllStopPlace_Requst = URL + "/CarLock/allweixin/getAllStopPlace"; //获取所有停车场车辆信息
    public static final String sendSMSNew_Requst = URL + "/CarLock/user/sendSMSNew"; //获取验证码登录
    public static final String login_Requst = URL + "/CarLock/user/smslogin"; //登录
    public static final String myOrder_Requst = URL + "/CarLock/order/getListByUserIdForMobile"; //我的订单
    public static final String predetermine_Requst = URL + "/CarLock/stopplace/getPredetermine"; //我的预定
    public static final String cancelPredetermine_Requst = URL + "/CarLock/allweixin/cancelPredetermine"; //取消预定
    public static final String getCarNumber_Requst = URL + "/CarLock/appUserInfo/getCarNumber"; //获取车牌号
    public static final String bindCarNumber_Requst = URL + "/CarLock/appUserInfo/bindCarNumber"; //绑定车牌号
    public static final String getStopPlaceAllMonthCardPrice_Requst = URL + "/CarLock/stopplace/getStopPlaceAllMonthCardPrice";//获取月卡信息
    public static final String getOrderInfo_Requst = URL +"/CarLock/appOrderInfo/getOrderInfo";//app充值下订单


    //接口常量
    public static final int getAllStopPlace = 0;  //获取所有停车场车辆信息
    public static final int sendSMSNew = 1;  //获取验证码登录
    public static final int login = 2;  //登录
    public static final int myOrder = 3;  //我的订单
    public static final int predetermine = 4;  //我的预定
    public static final int cancelPredetermine = 5;  //取消预定
    public static final int getCarNumber = 6;  //获取车牌号
    public static final int bindCarNumber = 7;  //绑定车牌号
    public static final int getStopPlaceAllMonthCardPrice = 8;  //获取月卡信息
    public static final int getOrderInfo = 9;  //app充值下订单


    //协议html地址
    public static final String agreement = URL +"/CarLock/html/rechsarge-agreement.html"; //充值协议


    /**
     * 文件下载存放目录
     */
    public static final String fileDirPath = Environment.getExternalStorageDirectory() + File.separator + "cwlm/Cache/" ;// 图片以及GIF文件存放目录


}
