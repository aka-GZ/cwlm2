package com.cwlm.capacitylock.ui.percenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;

import org.json.JSONObject;

import java.util.Calendar;


/**
 * 申请车位锁
 *
 * @author 1
 */
public class ApplyLockActivity extends BaseActivity implements View.OnClickListener {

    private EditText id_name;
    private EditText id_phone;
    private EditText id_address;
    private EditText id_extra;
    private LinearLayout id_commit;

    // 必须上升 开的才能分享 下降无法分享

    public ApplyLockActivity() {
        super(R.layout.act_applylock);
    }

    @Override
    public void getData() {}

    public void initView() {
        tv_title.setText("申请车位锁");

        id_name = (EditText) findViewById(R.id.id_name);
        id_phone = (EditText) findViewById(R.id.id_phone);
        id_address = (EditText) findViewById(R.id.id_address);
        id_extra = (EditText) findViewById(R.id.id_extra);
        id_commit = (LinearLayout) findViewById(R.id.id_commit);
        id_commit.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_commit:
                commit();
                break;

            default:
                break;
        }
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode){
            case InterfaceFinals.lockApply:
                showToast("提交成功，请等待工作人员与您联系！");
                id_name.setText("");
                id_phone.setText("");
                id_address.setText("");
                id_extra.setText("");
                break;
        }
    }

    private void commit() {
        final String name = id_name.getText().toString().trim();
        final String phone = id_phone.getText().toString().trim();
        final String address = id_address.getText().toString().trim();
        final String extra = id_extra.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            showToast("姓名或手机号不能为空");
            return;
        }

        getDataFromNet(InterfaceFinals.lockApply, true, name, phone, address, extra);


    }


}
