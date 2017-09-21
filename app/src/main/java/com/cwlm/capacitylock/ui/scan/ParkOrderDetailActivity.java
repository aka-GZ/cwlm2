package com.cwlm.capacitylock.ui.scan;


import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.CarLockAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.ParkOrderDetailModel;
import com.cwlm.capacitylock.obj.CarlocksObj;
import com.cwlm.capacitylock.obj.StopPlaceObj;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约车位
 */
public class ParkOrderDetailActivity extends BaseActivity implements View.OnClickListener {
    Button park_btn;
    RadioGroup radio_group;
    RadioButton radio_one, radio_two;
    TextView park_name;
    ListView park_list;

    String stopPlaceId;
    int routerId, addr;

    public ParkOrderDetailActivity() {
        super(R.layout.act_park_order_detail);
    }


    @Override
    public void getData() {
        stopPlaceId = (String) getIntent().getSerializableExtra("data");
        getDataFromNet(InterfaceFinals.getParkDetail, stopPlaceId);

    }

    int radio = -1;

    @Override
    public void initView() {
        tv_title.setText("车位预定");
        iv_right.setVisibility(View.INVISIBLE);

        park_name = (TextView) findViewById(R.id.park_name);
        park_btn = (Button) findViewById(R.id.park_btn);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_one = (RadioButton) findViewById(R.id.radio_one);
        radio_two = (RadioButton) findViewById(R.id.radio_two);
        park_list = (ListView) findViewById(R.id.park_list);

        park_btn.setOnClickListener(this);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == radio_one.getId()) {
                    radio = 1;
                } else if (checkedId == radio_two.getId()) {
                    radio = 2;
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.park_btn:
                if (Position == -1) {
                    showToast("请选择一个车位");
                    break;
                }
                if (radio == -1) {
                    showToast("请选择预定时长");
                    break;
                }

                //1、支付宝支付  2、微信支付  3、余额支付
                getDataFromNet(InterfaceFinals.advanceParkByApp, user.getUserId(), routerId + "", addr + "", stopPlaceId, radio + "", "3");

                break;

        }
    }


    int Position = -1;
    View OldView = null;
    List<CarlocksObj> list;
    CarLockAdapter adapter;

    @Override
    public void onSuccess(BaseModel resModel) {
        int infcode = resModel.getInfCode();
        switch (infcode) {
            case InterfaceFinals.getParkDetail:
                ParkOrderDetailModel model = (ParkOrderDetailModel) resModel;
                StopPlaceObj stopPlaceObj = model.getMap().getStopPlace();
                list = model.getMap().getCarlocks();

                park_name.setText(stopPlaceObj.getName());
                adapter = new CarLockAdapter(ParkOrderDetailActivity.this, list);
                park_list.setAdapter(adapter);
                park_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                        if (OldView == null) {
//                            OldView = view;
//                            ChangeNewView(view, true);
//                        } else {
//                            ChangeNewView(OldView, false);
//                            ChangeNewView(view, true);
//                            OldView = view;
//                        }

                        for (int i = 1; i <= list.size(); i++) {

                            CarLockAdapter.isSelected.put(i,false);
                        }

                        CarLockAdapter.isSelected.put(position,true);

                        if (position > 0){
                            Position = position-1;
                            routerId = list.get(Position).getRouterId();
                            addr = list.get(Position).getAddr();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                break;

            case InterfaceFinals.advanceParkByApp:

                showToast("预约成功！");
                finish();

                break;
        }
    }

    private void ChangeNewView(View view, Boolean isNew) {

        CheckBox item_park_selected = (CheckBox) view.findViewById(R.id.item_park_selected);

        if (isNew) {
            item_park_selected.setChecked(true);
        } else {
            item_park_selected.setChecked(false);
        }

    }


}
