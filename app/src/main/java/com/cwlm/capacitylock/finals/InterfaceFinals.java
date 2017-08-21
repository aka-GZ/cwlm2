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
//    public static final String login_Requst = URL + "/CarLock/user/smslogin"; //登录 - 旧
    public static final String login_Requst = URL + "/CarLock/user/newsmsLogin"; //登录
    public static final String myOrder_Requst = URL + "/CarLock/order/getListByUserIdForMobile"; //我的订单
    public static final String predetermine_Requst = URL + "/CarLock/stopplace/getPredetermine"; //我的预定
    public static final String cancelPredetermine_Requst = URL + "/CarLock/allweixin/cancelPredetermine"; //取消预定
    public static final String getCarNumber_Requst = URL + "/CarLock/appUserInfo/getCarNumber"; //获取车牌号
    public static final String bindCarNumber_Requst = URL + "/CarLock/appUserInfo/bindCarNumber"; //绑定车牌号
    public static final String getStopPlaceAllMonthCardPrice_Requst = URL + "/CarLock/stopplace/getStopPlaceAllMonthCardPrice";//获取月卡信息
    public static final String getMonthcardOrderInfo_Requst = URL +"/CarLock/appOrderInfo/getMonthcardOrderInfo";//app购买月卡下订单
    public static final String getAllUserMonthCard_Requst = URL +"/CarLock/stopplace/getAllUserMonthCard";//获取月卡充值记录
    public static final String getSweepNumber_Requst = URL +"/CarLock/appUserInfo/getSweepNumber"; //主页获取押金和使用次数
    public static final String scanCode_Requst = URL +"/CarLock/stopplace/scanCode";//app扫码停车_新
    public static final String cancelPark_Requst = URL +"/CarLock/stopplace/cancelPark";//app取消停车
    public static final String lockApply_Requst = URL +"/CarLock/lock/lockApply";//申请车位锁
    public static final String appGetMyBalance_Requst = URL +"/CarLock/wallet/appGetMyBalance";//app获取余额
    public static final String saveAdvice_Requst = URL +"/CarLock/user/saveAdvice"; //投诉意见
//    public static final String getRentCarLockInfo_Requst = URL +"/CarLock/proprietor/getRentCarLockInfoForUserId";//app查询我的车位（旧）
    public static final String getCarLockUseInformation_Requst = URL +"/CarLock/lock/getCarLockUseInformation";//app查询我的车位（新）
    public static final String appBindingUser_Requst = URL +"/CarLock/user/appBindingUser";//绑定我的车位
    public static final String updateCarLockState_Requst = URL +"/CarLock/lock/updateCarLockState";//分享我的车位
    public static final String getRechargeOrderInfo_Requst = URL +"/CarLock/appOrderInfo/getRechargeOrderInfo";//app充值下订单

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
    public static final int getMonthcardOrderInfo = 9;  //app购买月卡下订单
    public static final int getAllUserMonthCard = 10;  //获取月卡充值记录
    public static final int getSweepNumber = 11;  //主页获取押金和使用次数
    public static final int scanCode = 12;  //app扫码停车_新
    public static final int cancelPark = 13;  //app取消停车
    public static final int lockApply = 14;  //申请车位锁
    public static final int appGetMyBalance = 15;  //app获取余额
    public static final int saveAdvice = 16;  //投诉意见
//    public static final int getCarLockUseInformation = 17; //app查询我的车位（旧）
    public static final int getCarLockUseInformation = 17; //app查询我的车位（新）
    public static final int appBindingUser = 18; //app绑定我的车位
    public static final int updateCarLockState = 19; //分享我的车位
    public static final int getRechargeOrderInfo = 20; //app充值下订单

    //协议html地址
    public static final String agreement = URL +"/CarLock/html/rechsarge-agreement.html"; //充值协议
    public static final String register_agreement = URL +"/CarLock/html/register-agreement.html"; //登录注册协议


    public static final String service = "http://zylm.net/weixin/lockApply.html"; //主页我的客服地址

    /**
     * 文件下载存放目录
     */
    public static final String fileDirPath = Environment.getExternalStorageDirectory() + File.separator + "cwlm/Cache/" ;// 图片以及GIF文件存放目录


}
