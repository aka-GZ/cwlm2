package com.cwlm.capacitylock.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.UserObj;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.ui.percenter.BindCarNumbleActivity;
import com.cwlm.capacitylock.ui.zxing.activity.CaptureActivity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by akawok on 2017-08-01.
 */
public class MyUtils {

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
     * 17+除9的任意数 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(1)\\d{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 车牌号正则表达式（包含新车牌）
     **/
    public static Boolean isCarNumber(String cph) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5|WJ]{1}[A-Z0-9]{6,7}$");
        Matcher matcher = pattern.matcher(cph);
        return matcher.matches();
    }


    /**
     * 判断文件或文件夹是否存在
     *
     * @param path 文件夹路径
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在
        if (!file.exists()) {
            //file.mkdir(); //不存在创建文件夹
            return false;
        }
        return true;
    }

    /**
     * 判断是否登录
     *
     * @param ctx
     */
    public static boolean isLogin(Context ctx) {

        UserObj user = (UserObj) PreferencesUtil.getPreferences(ctx, "User");
        if (user == null || TextUtils.isEmpty(user.getPhoneNum())) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public static void call(Context ctx, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    /**
     * MD5转换
     *
     * @param str
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] b = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int v = (int) b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1)
                    sb.append('0');
                sb.append(cc);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return "V" + versionName;
    }


    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }


    /**
     * 启动Jpush服务
     */
    public static void StartJpushService(Context context) {
        if (JPushInterface.isPushStopped(context)) {
            JPushInterface.resumePush(context); //启动Jpush服务
        } else {
        }
    }

    /**
     * 停止Jpush服务并删除别名
     */
    public static void StopJpushService(Context context) {
        if (!JPushInterface.isPushStopped(context)) {
            JPushInterface.stopPush(context); //停止Jpush服务
//            JPushInterface.deleteAlias(context,0); //删除别名
        }
    }

}
