package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.CarlocksObj;

import java.util.HashMap;
import java.util.List;


/**
 * Created by akawok on 2017-03-31.
 */
public class CarLockAdapter extends BaseAdapter {
    private Context context;
    List<CarlocksObj> carLocks;

    public static HashMap<Integer, Boolean> isSelected;

    public CarLockAdapter(Context context, List<CarlocksObj> carLocks) {
        this.context = context;
        this.carLocks = carLocks;
        init();
    }


    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 1; i <= carLocks.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
       if(carLocks==null){
           return 0;
       }else{
           return carLocks.size()+1;
       }
    }

    @Override
    public Object getItem(int position) {
        if (carLocks==null){
            return null;
        }else{
            return carLocks.get(position-1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position-1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_park_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.item_park_num= (TextView) view.findViewById(R.id.item_park_num);
            viewHolder.item_park_time= (TextView) view.findViewById(R.id.item_park_time);
            viewHolder.item_park_address= (TextView) view.findViewById(R.id.item_park_address);
            viewHolder.item_park_detail= (LinearLayout) view.findViewById(R.id.item_park_detail);
            viewHolder.item_park_selected= (CheckBox) view.findViewById(R.id.item_park_selected);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(position==0){
            viewHolder.item_park_num.setText("车位号");viewHolder.item_park_num.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.item_park_time.setText("截止使用时间"); viewHolder.item_park_time.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.item_park_address.setText("车位位置(层)");viewHolder.item_park_address.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.item_park_selected.setVisibility(View.GONE);
            viewHolder.item_park_detail.setBackgroundColor(Color.parseColor("#003377"));
        }else{
            viewHolder.item_park_num.setText(carLocks.get(position-1).getParkNumber());
            viewHolder.item_park_time.setText(carLocks.get(position-1).getEndHourTime());
            viewHolder.item_park_address.setText(carLocks.get(position-1).getFloor());
            viewHolder.item_park_selected.setChecked(isSelected.get(position));
        }
        return view;
    }
    static class ViewHolder {
       TextView item_park_num ,item_park_address,item_park_time;
        LinearLayout item_park_detail;
        CheckBox item_park_selected;
    }
}