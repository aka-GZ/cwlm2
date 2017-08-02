package com.cwlm.capacitylock.ui;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Set;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.obj.UserObj;
import com.cwlm.capacitylock.utils.CountDownTimerUtils;
import com.cwlm.capacitylock.utils.MyDialog;
import com.cwlm.capacitylock.utils.MyUtils;
import com.cwlm.capacitylock.utils.PreferencesUtil;
import com.cwlm.capacitylock.utils.SmsContent;

import cn.jpush.android.api.JPushInterface;

/**
 * 登陆Activity
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private LinearLayout id_delete_username, id_login;
    private final static int SUCCESS = 1;
    private final static int WEBFAIL = 2;
    //    private ImageButton id_back;
    private EditText id_username, id_code;
    private ProgressDialog dialog;
    private SmsContent smsContent;
    private TextView term_of_service, id_getcode;

    public LoginActivity() {
        super(R.layout.act_login);
    }

    @Override
    public void getData() {

    }


    public void initView() {
        id_code = (EditText) findViewById(R.id.id_code);
        smsContent = new SmsContent(new Handler(), LoginActivity.this, id_code);
        getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, smsContent);
//        通过广播来获取短信填充二维码，此方法有可能被手机报病毒 使用ContentObserver更安全
//        receiver = new SMSReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        filter.setPriority(Integer.MAX_VALUE);
//        // filter.setPriority(100);
//        registerReceiver(receiver, filter);


        term_of_service = (TextView) findViewById(R.id.term_of_service);
        term_of_service.setOnClickListener(this);
        id_getcode = (TextView) findViewById(R.id.id_getcode);
        id_getcode.setOnClickListener(this);
        id_delete_username = (LinearLayout) findViewById(R.id.id_delete_username);
        id_delete_username.setOnClickListener(this);
        id_username = (EditText) findViewById(R.id.id_username);
        id_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    id_delete_username.setVisibility(View.VISIBLE);
                } else {
                    id_delete_username.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        id_login = (LinearLayout) findViewById(R.id.id_login);
        id_login.setOnClickListener(this);

        tv_title.setText("登录");
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.sendSMSNew:
                if ("1".equals(resModel.getStatusCode())) {
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(id_getcode, 60000, 1000);
                    mCountDownTimerUtils.start();
                } else {
                    showToast(resModel.getMess());
                }

                break;

            case InterfaceFinals.login:
                if ("1".equals(resModel.getStatusCode())) {
                    UserObj user = (UserObj)resModel;
                    PreferencesUtil.setPreferences(LoginActivity.this, "User", user);
                    //给jpush当做操作唯一标识
                    JPushInterface.setAlias(LoginActivity.this, 0 , user.getPhoneNum());
                    MyUtils.StartJpushService(getApplication());

                } else {
                    showToast(resModel.getMess());
                }


                break;
        }

    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_getcode:
                if (!MyUtils.isPhoneLegal(id_username.getText().toString())) {
                    new AlertDialog.Builder(this)
                            .setTitle("信息")
                            .setMessage("请输入正确的手机号")
                            .setPositiveButton("确定", null)
                            .show();
                    break;
                }

                String username = id_username.getText().toString().trim();
                getDataFromNet(InterfaceFinals.sendSMSNew,true , username, "REGISTER");

//                RequestParams params1 = MyApplication.getRequestParams();
//                params1.put("toUser", id_username.getText().toString().trim());
//                params1.put("type", "REGISTER");
//                ZylmRestClient.post(Constants.request_url13, params1, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        ResultData resultData = JSON.parseObject(response.toString(), ResultData.class);
//                        if ("1".equals(resultData.getStatusCode())) {
//                            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(id_getcode, 60000, 1000);
//                            mCountDownTimerUtils.start();
//                        } else {
//                            ToastUtil.show(LoginActivity.this, resultData.getMess());
//                        }
//                    }
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        ToastUtil.show(LoginActivity.this, Constants.ZYLM_WARN);
//                    }
//                });
                break;
            case R.id.id_delete_username:
                // 如果选中
                id_username.setText("");
                break;
            // 登陆
            case R.id.id_login:
                if (!MyUtils.isPhoneLegal(id_username.getText().toString())) {
                    new AlertDialog.Builder(this)
                            .setTitle("信息")
                            .setMessage("请输入正确的手机号")
                            .setPositiveButton("确定", null)
                            .show();
                    break;
                }

                id_delete_username.setVisibility(View.INVISIBLE);
                final String username_login = id_username.getText().toString().trim();
                final String code = id_code.getText().toString().trim();
                if (TextUtils.isEmpty(username_login) || TextUtils.isEmpty(code)) {
                    showToast("请输入手机号和验证码");
                    break;
                }

                    getDataFromNet(InterfaceFinals.login, true, username_login, code);


//                RequestParams params = MyApplication.getRequestParams();
//                params.put("phoneNum", username);
//                params.put("idenCode", code);
////                        params.put("carNumber","鄂ASDCVF");
//                ZylmRestClient.post(Constants.request_url55, params, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        String s = response.toString();
//                        Log.e("LoginResponse", s);
//                        ResultData resultData = JSON.parseObject(s, ResultData.class);
//                        if ("1".equals(resultData.getMess())) {
//                            User user = JSON.parseObject(resultData.getObject().toString(), User.class);
//                            String userId = user.getUserId();
////                                    long update_client_time = System.currentTimeMillis();
//                            SharedPreferences share = MyApplication.MygetSharedPreferences();
//                            final SharedPreferences.Editor edit = share.edit();
//                            edit.putInt("isReceive", user.getIsReceive());
//                            edit.putInt("isInform", user.getIsInform());
//                            edit.putString("userId", userId);
//                            edit.putString("carNumber", user.getCarNumber());
//                            edit.putString("phoneNum", user.getPhoneNum());
//                            edit.commit();
//                            // 极光推送设置别名
//                            boolean issetalias = share.getBoolean("alias", false);
//                            if (!issetalias) {
//                                JPushInterface.setAlias(getApplicationContext(), userId,
//                                        new TagAliasCallback() {
//                                            @Override
//                                            public void gotResult(int code, String alias, Set<String> tags) {
//                                                switch (code) {
//                                                    // 设置成功
//                                                    case 0:
//                                                        edit.putBoolean("alias", true);
//                                                        edit.commit();
//                                                        break;
//                                                    // 设置失败
//                                                    case 6002:
//                                                        break;
//                                                    default:
//                                                        break;
//                                                }
//                                            }
//                                        });
//                            }
////                                    Intent intent = new Intent(LoginActivity.this, MainActivity_Drawer.class);
////                                    startActivity(intent);
//                            finish(true);
//                            overridePendingTransition(R.anim.activity_open, R.anim.activity_bd);
//                            // 获取租用地锁
//                        } else if ("2".equals(resultData.getMess())) {
//                            dialog.dismiss();
//                            ToastUtil.show(LoginActivity.this, "用户名或密码错误");
//                        } else if ("3".equals(resultData.getMess())) {
//                            dialog.dismiss();
//                            ToastUtil.show(getApplicationContext(), "验证码不能为空!");
//                            return;
//                        } else if ("4".equals(resultData.getMess())) {
//                            dialog.dismiss();
//                            ToastUtil.show(LoginActivity.this, "验证码错误或者过期!");
//                            return;
//                        }
//                        return;
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        ToastUtil.show(LoginActivity.this, Constants.ZYLM_WARN);
//                    }
//                });

                break;
            case R.id.term_of_service:

                break;
            default:
                break;
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    dialog.dismiss();
                    // finish(true);
                    break;
                case WEBFAIL:
                    dialog.dismiss();
                    showToast("网络异常");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
        }
        //unregisterReceiver(receiver);//注销广播
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder alertbBuilder = new AlertDialog.Builder(this);
            alertbBuilder.setTitle("真的要离开？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 结束这个Activity
                    //  UpdateManager.setCancelUpdate(true);
                    int currentVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        System.exit(0);
                    } else {// android2.1
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        am.restartPackage(getPackageName());
                    }
                    /*
                    int nPid = android.os.Process.myPid();
                    android.os.Process.killProcess(nPid);
                    System.exit(0);
                    */
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            alertbBuilder.show();
            return false;// 消费掉后退键
        }
        return super.onKeyDown(keyCode, event);
    }


//    private SMSReceiver receiver;
//    String strFrom;
//    String strMsg;
//
//    class SMSReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            Bundle bundle = intent.getExtras();
//            if (bundle != null) {
//                Object[] pdus = (Object[]) bundle.get("pdus");
//                SmsMessage[] messages = new SmsMessage[pdus.length];
//                for (int i = 0; i < pdus.length; i++) {
//                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                }
//
//                for (SmsMessage message : messages) {
//                    strFrom = message.getDisplayOriginatingAddress();
//                    strMsg = message.getDisplayMessageBody();
//                }
//                handler.sendEmptyMessage(123);
//                id_code.setText("");
//            }
//
//
//            Log.e("MESSAGE", "==================================== "+strFrom + ":" + strMsg);
//
//        }
//    }
}