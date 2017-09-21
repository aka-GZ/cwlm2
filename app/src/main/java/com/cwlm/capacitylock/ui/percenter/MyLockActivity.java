package com.cwlm.capacitylock.ui.percenter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.MyLockAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.MyLocksModel;
import com.cwlm.capacitylock.obj.MyLocksObj;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的车位
 * Created by akawok on 2017-08-14.
 */
public class MyLockActivity extends BaseActivity implements MyLockAdapter.MyClickListener {


    ListView mylock_lv;
    TextView mylock_tv;
    MyLockAdapter adapter;
    List<MyLocksObj> list = new ArrayList<MyLocksObj>();

    public MyLockActivity() {
        super(R.layout.act_mylock);
    }

    @Override
    public void getData() {

    }

    @Override
    protected void onResume() {
        getDataFromNet(InterfaceFinals.getCarLockUseInformation, user.getUserId());
        super.onResume();
    }

    public void initView() {
        tv_title.setText("我的车位");
        iv_right.setVisibility(View.INVISIBLE);

        mylock_lv = (ListView) findViewById(R.id.mylock_lv);
        mylock_tv = (TextView) findViewById(R.id.mylock_tv);

        mylock_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BindMyLockActivity.class);
            }
        });


        adapter = new MyLockAdapter(MyLockActivity.this, list, this);
        mylock_lv.setAdapter(adapter);
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode) {
            case InterfaceFinals.getCarLockUseInformation:
                list.clear();
                list.addAll(((MyLocksModel) resModel).getObject());
                if (list != null || !list.isEmpty()) {
                    // adapter = new MyLockAdapter(MyLockActivity.this, list);
                    mylock_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    showToast("暂无绑定车位");
                }

                break;

            case InterfaceFinals.updateCarLockState:
                getDataFromNet(InterfaceFinals.getCarLockUseInformation, user.getUserId());
                showToast("状态修改成功！");
                break;
        }
    }


    @Override
    public void clickListener(final String isShare, final String CarLockId) {
        if ("0".equals(isShare)) {  //1为未分享
            //获取当前时间 (import  java.text.SimpleDateFormat;)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                    Calendar ca = Calendar.getInstance();
//                    ca.set(Calendar.HOUR, ca.get(Calendar.HOUR) + 2);
            Date curDate = new Date(System.currentTimeMillis() + 21600000);//21600000
            String date = formatter.format(curDate);
            TimeSelector timeSelector = new TimeSelector(MyLockActivity.this, new TimeSelector.ResultHandler() {
                @Override
                public void handle(String time) {
                    showToast(time);
//                            time_status = 1;
                    getDataFromNet(InterfaceFinals.updateCarLockState, user.getUserId(), CarLockId, isShare, time.toString().trim());

                }
            }, date, "2050-12-31 23:59:59");
            timeSelector.setIsLoop(false);//设置不循环,true循环
            timeSelector.setMode(TimeSelector.MODE.YMDHM);//显示 年月日时分（默认）
//                  timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
            timeSelector.show();

        } else {

            final Dialog dialog = new Dialog(MyLockActivity.this, R.style.mydialog);
            dialog.setContentView(R.layout.dialog);
            TextView dialog_skip = (TextView) dialog.findViewById(R.id.dialog_skip);
            TextView dialog_go = (TextView) dialog.findViewById(R.id.dialog_go);
            final TextView dialog_text = (TextView) dialog.findViewById(R.id.dialog_text);
            dialog_skip.setText("确定");
            dialog_go.setText("取消");
            dialog_text.setText("              是否确定关闭分享？              ");
            dialog.show();

            dialog_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    getDataFromNet(InterfaceFinals.updateCarLockState, user.getUserId(), CarLockId, isShare, "");
                }
            });
            dialog_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
        }
    }
}
