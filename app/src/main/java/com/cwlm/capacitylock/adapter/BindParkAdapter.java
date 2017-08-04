package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.BindParkObj;

import java.util.List;

/**
 *
 */
public class BindParkAdapter extends BaseAdapter {
    private Context context;
    List<BindParkObj> list;

    public BindParkAdapter(Context context, List<BindParkObj> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        } else {
            return list.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_bindpark, null);
            viewHolder = new ViewHolder();
            viewHolder.bindpark_item_tv_1 = (TextView) view.findViewById(R.id.bindpark_item_tv_1);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.recharge_item_ll.setBackgroundResource(R.drawable.mywallet_recharge_pay);
//        viewHolder.recharge_item_tv_1.setTextColor(context.getResources().getColor(R.color.background));
//        viewHolder.recharge_item_tv_2.setTextColor(context.getResources().getColor(R.color.background));
        viewHolder.bindpark_item_tv_1.setText(list.get(position).getName());


        return view;
    }

    class ViewHolder {
        TextView bindpark_item_tv_1;
    }
}
