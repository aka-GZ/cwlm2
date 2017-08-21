package com.cwlm.capacitylock.ui.percenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.ui.LoginActivity;
import com.cwlm.capacitylock.ui.MainActivity;
import com.cwlm.capacitylock.ui.ServiceWebView;
import com.cwlm.capacitylock.utils.MyUtils;
import com.cwlm.capacitylock.utils.PreferencesUtil;

/**
 * Created by akawok on 2017-08-08.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    TextView setting_phonenum_tv, setting_bind_carnumber_tv, setting_about_tv , setting_agreement;
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
        iv_right.setVisibility(View.INVISIBLE);


        logout= (Button) findViewById(R.id.logout);
        setting_phonenum_tv= (TextView) findViewById(R.id.setting_phonenum_tv);
        setting_bind_carnumber_tv= (TextView) findViewById(R.id.setting_bind_carnumber_tv);
        setting_about_tv= (TextView) findViewById(R.id.setting_about_tv);
        setting_agreement= (TextView) findViewById(R.id.setting_agreement);

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
        setting_about_tv.setText(MyUtils.getAppVersionName(SettingActivity.this));

        logout.setOnClickListener(this);
//        setting_phonenum_rl.setOnClickListener(this);
//        setting_bind_carnumber_rl.setOnClickListener(this);
        setting_about_rl.setOnClickListener(this);
        setting_agreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
//                Ask();
                showDialog("                确认退出登录？                ");
                break;
            case R.id.setting_about_rl:
                startActivity(IntroduceActivity.class);
                break;
            case R.id.setting_agreement:
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, ServiceWebView.class);
                intent.putExtra("LoadUrl", InterfaceFinals.register_agreement);
                startActivity(intent);
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

                MyUtils.StopJpushService(getApplication());
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

    public void showDialog(String msg){

        final Dialog dialog = new Dialog(SettingActivity.this,R.style.mydialog);
        dialog.setContentView(R.layout.base_actvity_dialog);
        dialog.show();
        dialog.findViewById(R.id.base_dialog_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.findViewById(R.id.base_dialog_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.isShowing()) {
                    dialog.dismiss();
                }
                PreferencesUtil.clearPreferences(SettingActivity.this , "User");

                MyUtils.StopJpushService(getApplication());
                startActivity(MainActivity.class);
            }
        });
        TextView base_dialog_text = (TextView) dialog.findViewById(R.id.base_dialog_text);
        base_dialog_text.setText(msg);
    }
}