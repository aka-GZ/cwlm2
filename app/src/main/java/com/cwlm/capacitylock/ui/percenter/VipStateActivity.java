package com.cwlm.capacitylock.ui.percenter;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.VipStateAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.VipStateModel;

/**
 * Created by akawok on 2017-08-05.
 */
public class VipStateActivity extends BaseActivity {


    ListView vipstate_lv;
    VipStateAdapter adapter;

    public VipStateActivity() {
        super(R.layout.act_vipstate);
    }


    @Override
    public void getData() {
        getDataFromNet(InterfaceFinals.getAllUserMonthCard, user.getUserId());
    }


    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getAllUserMonthCard://获取月卡充值记录
                VipStateModel model = (VipStateModel) resModel;
                adapter = new VipStateAdapter(VipStateActivity.this, model.getObject());
                vipstate_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                break;
        }
    }


    public void onClick(View v) {
//        Intent intent = new Intent();
//        switch (v.getId()) {
//             case R.id.menu_user_img:      //用户头像
//
//            default:
//                break;
//        }
    }

    public void initView() {
        tv_title.setText("月卡查询");
        iv_right.setVisibility(View.INVISIBLE);

        vipstate_lv = (ListView) findViewById(R.id.vipstate_lv);
    }



}
