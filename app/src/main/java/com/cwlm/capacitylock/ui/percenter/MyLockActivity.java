package com.cwlm.capacitylock.ui.percenter;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akawok on 2017-08-14.
 */
public class MyLockActivity extends BaseActivity{


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
        getDataFromNet(InterfaceFinals.getRentCarLockInfo, user.getUserId());
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


        adapter = new MyLockAdapter(MyLockActivity.this, list);
        mylock_lv.setAdapter(adapter);
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode){
            case InterfaceFinals.getRentCarLockInfo:
                list.clear();
                list.addAll(((MyLocksModel)resModel).getObject());
                if (list != null || !list.isEmpty()) {
                    // adapter = new MyLockAdapter(MyLockActivity.this, list);
                    mylock_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    showToast("暂无绑定车位");
                }

                break;
        }
    }

}
