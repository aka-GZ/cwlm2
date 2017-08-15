package com.cwlm.capacitylock.ui.percenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.utils.PreferencesUtil;

/**
 * Created by akawok on 2017-08-08.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    TextView setting_phonenum_tv, setting_bind_carnumber_tv, setting_about_tv;
    Button logout;
    RelativeLayout setting_phonenum_rl, setting_bind_carnumber_rl, setting_about_rl;

    public SettingActivity() {
        super(R.layout.act_setting);
    }

    @Override
    public void getData() {

    }

    public void initView() {

        tv_title.setText("设置");


        logout= (Button) findViewById(R.id.logout);
        setting_phonenum_tv= (TextView) findViewById(R.id.setting_phonenum_tv);
        setting_bind_carnumber_tv= (TextView) findViewById(R.id.setting_bind_carnumber_tv);
        setting_about_tv= (TextView) findViewById(R.id.setting_about_tv);

        setting_phonenum_rl= (RelativeLayout) findViewById(R.id.setting_phonenum_rl);
        setting_bind_carnumber_rl= (RelativeLayout) findViewById(R.id.setting_bind_carnumber_rl);
        setting_about_rl= (RelativeLayout) findViewById(R.id.setting_about_rl);

        //显示手机号
        if (user == null || TextUtils.isEmpty(user.getPhoneNum())) {
            setting_phonenum_tv.setText("未登录");
        } else {
            String PhoneNum = user.getPhoneNum();
            setting_phonenum_tv.setText(PhoneNum.substring(0, 3) + "****" + PhoneNum.substring(7));
            if (!TextUtils.isEmpty(user.getCarNumber())) {
                setting_bind_carnumber_tv.setText(user.getCarNumber());
            }
        }

        logout.setOnClickListener(this);
//        setting_phonenum_rl.setOnClickListener(this);
//        setting_bind_carnumber_rl.setOnClickListener(this);
        setting_about_rl.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                Ask();
                break;
            case R.id.setting_about_rl:
                startActivity(IntroduceActivity.class);
                break;
            default:
                break;
        }
    }
    private void Ask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出登录？");
        builder.setCancelable(true);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferencesUtil.clearPreferences(SettingActivity.this , "User");
                startActivity(MainActivity.class);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}