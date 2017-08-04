package com.cwlm.capacitylock.ui.percenter;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.BindParkAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.BindParkModel;
import com.cwlm.capacitylock.obj.BindParkObj;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akawok on 2017-08-04.
 */
public class BindParkActivity extends BaseActivity {


    ListView bindpark_lv;
    BindParkAdapter adapter;

    List<BindParkObj> list = new ArrayList<BindParkObj>();

    public BindParkActivity() {
        super(R.layout.act_bindpark);
    }

    public void initView() {
        tv_title.setText("选择停车场");
        iv_right.setVisibility(View.INVISIBLE);

        bindpark_lv = (ListView) findViewById(R.id.bindpark_lv);
    }


    public void getData() {
        getDataFromNet(InterfaceFinals.getAllStopPlace);
    }
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getAllStopPlace:
                list.clear();
                list.addAll(((BindParkModel) resModel).getObject());
                adapter = new BindParkAdapter(BindParkActivity.this , list);
                bindpark_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                bindpark_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(BindParkActivity.this, BindCarNumbleActivity.class);
                        intent.putExtra("data",list.get(position).getStopPlaceId());
                        startActivity(intent);
                        //overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
                        finish();

                    }
                });
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
//        overridePendingTransition(R.anim.push_null_in, R.anim.push_left_out);
        return true;
    }


}
