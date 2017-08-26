package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.CarlocksObj;

import java.util.List;


/**
 * Created by akawok on 2017-03-31.
 */
public class CarLockAdapter extends BaseAdapter {
    private Context context;
    List<CarlocksObj> carLocks;

    public CarLockAdapter(Context context, List<CarlocksObj> carLocks) {
        this.context = context;
        this.carLocks = carLocks;
    }

    @Override
    public int getCount() {
       if(carLocks==null){
           return 0;
       }else{
           return carLocks.size();
       }
    }

    @Override
    public Object getItem(int position) {
        if (carLocks==null){
            return null;
        }else{
            return carLocks.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_park_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.item_park_num= (TextView) view.findViewById(R.id.item_park_num);
            viewHolder.item_park_address= (TextView) view.findViewById(R.id.item_park_address);
            viewHolder.item_park_detail= (LinearLayout) view.findViewById(R.id.item_park_detail);
//            viewHolder.item_park_selected= (CheckBox) view.findViewById(R.id.item_park_selected);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.item_park_num.setText(carLocks.get(position).getParkNumber());
        viewHolder.item_park_address.setText(carLocks.get(position).getFloor() + "层");
//        viewHolder.item_park_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return view;
    }
    static class ViewHolder {
       TextView item_park_num ,item_park_address;
        LinearLayout item_park_detail;
//        CheckBox item_park_selected;
    }
}