package com.cwlm.capacitylock.utils;

/**
 * copy by akawok on 2017/08/01.
 */


import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *获取短信内容，包括验证码
 */
public class SmsContent extends ContentObserver {
    private Cursor cursor = null;
    private Activity activity;
    private EditText editText;
    public SmsContent(Handler handler, Activity activity, EditText editText) {
        super(handler);
        this.activity = activity;
        this.editText = editText;
    }
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        cursor = activity.managedQuery(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "body", "read"},
                 null, null, "_id desc");
        if (cursor != null && cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("read", "1");
            cursor.moveToNext();
            int smsbodyColumn = cursor.getColumnIndex("body");
            String smsBody = cursor.getString(smsbodyColumn);
            editText.setText(getDynamicPassword(smsBody));
        }
        if(Build.VERSION.SDK_INT < 14) {
            cursor.close();
        }
    }
    public static String getDynamicPassword(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while(m.find()){
            if(m.group().length() == 4) {
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }
}
