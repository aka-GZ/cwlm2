package com.cwlm.capacitylock.ui.percenter;

import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;

/**
 * Created by Administrator on 2017-06-02.
 */
public class BindMyLockActivity extends BaseActivity {


    EditText bindmylock_et;
    TextView bindmylock_tv;

    public BindMyLockActivity() {
        super(R.layout.act_bindmylock);
    }

    @Override
    public void getData() {

    }

    public void initView() {
        tv_title.setText("绑定我的车位");
        iv_right.setVisibility(View.INVISIBLE);

        bindmylock_et = (EditText) findViewById(R.id.bindmylock_et);
        bindmylock_tv = (TextView) findViewById(R.id.bindmylock_tv);

        bindmylock_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bind();

            }
        });
    }


    public void getdata() {
    }


    public void Bind() {

        String key = bindmylock_et.getText().toString().trim();

        if (key !=null || !"".equals(key)){
            getDataFromNet(InterfaceFinals.appBindingUser , user.getUserId() , key);
        }else{
            showToast("请输入正确的车位私钥");
        }
    }
    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode){
            case InterfaceFinals.appBindingUser:

                showToast("绑定车位成功");

                break;
        }
    }
}
