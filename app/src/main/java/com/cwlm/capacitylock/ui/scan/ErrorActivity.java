package com.cwlm.capacitylock.ui.scan;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.adapter.NearCarLockAdapter;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.obj.CarLockObj;
import com.cwlm.capacitylock.obj.FreeLockObj;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * Created by Administrator on 2017-05-09.
 */
public class ErrorActivity extends BaseActivity {


    ListView error_lv;

    public ErrorActivity() {
        super(R.layout.act_error);
    }


    public void getData() {

        String freelock = getIntent().getStringExtra("freelock");


        try {
            FreeLockObj flObj = new Gson().fromJson(freelock, FreeLockObj.class);
            List<CarLockObj> carLocks = flObj.getObject();
            if (carLocks!=null&&!carLocks.isEmpty()) {

                NearCarLockAdapter nearCarLockAdapter = new NearCarLockAdapter(ErrorActivity.this, carLocks);

                error_lv.setAdapter(nearCarLockAdapter);
                setListViewHeightBasedOnChildren(error_lv);
                nearCarLockAdapter.notifyDataSetChanged();
            }else{
               showToast("暂无推荐停车位，请重试");
            }
        } catch (JsonSyntaxException e) {
            showToast("服务器数据升级，请重试");
            e.printStackTrace();
        }

    }

    @Override
    public void initView() {
        findViewById(R.id.error_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Anim();
            }
        });


        error_lv = (ListView) findViewById(R.id.error_lv);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
         // 获取ListView对应的Adapter
         ListAdapter listAdapter = listView.getAdapter();
              if (listAdapter == null) {
                      return;
                  }


         int totalHeight = 0;

        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                       // listAdapter.getCount()返回数据项的数目
                      View listItem = listAdapter.getView(i, null, listView);
              // 计算子项View 的宽高
                     listItem.measure(0, 0);
                     // 统计所有子项的总高度
                    totalHeight += listItem.getMeasuredHeight();
                }

              ViewGroup.LayoutParams params = listView.getLayoutParams();
               params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
          // listView.getDividerHeight()获取子项间分隔符占用的高度
              // params.height最后得到整个ListView完整显示需要的高度
             listView.setLayoutParams(params);
          }

}








