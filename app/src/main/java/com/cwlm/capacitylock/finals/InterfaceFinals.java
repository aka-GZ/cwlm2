package com.cwlm.capacitylock.finals;

/**
 * Created by akawok on 2017-07-28.
 */
public class InterfaceFinals {

//    public static final String URL = "http://zylm.net:80"; //正式服务器
    public static final String URL = "http://wooowoo.cn";  //测试服务器

    //请求URL
    public static final String getAllStopPlace_Requst= URL + "/CarLock/allweixin/getAllStopPlace"; //获取所有停车场车辆信息
    public static final String sendSMSNew_Requst= URL + "/CarLock/user/sendSMSNew"; //获取验证码登录
    public static final String login_Requst= URL + "/CarLock/user/smslogin"; //登录


    //接口常量
    public static final int getAllStopPlace = 0;  //获取所有停车场车辆信息
    public static final int sendSMSNew = 1;  //获取所有停车场车辆信息
    public static final int login = 2;  //获取所有停车场车辆信息

}
