package com.cwlm.capacitylock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.obj.OrderInfoObj;

import java.util.List;

/**
 * 订单列表
 * Created by akawok on 2017-08-08.
 */
public class OrderInfoAdapter extends BaseAdapter {
    private Context context;
    private List<OrderInfoObj> list;
    public OrderInfoAdapter(Context context, List<OrderInfoObj> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        if (list!=null&&list.size()>0){
            return list.get(position);
        }else{
            return null;
        }
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_orderinfo, null);
            viewHolder = new ViewHolder();
            viewHolder.stopplaceName= (TextView) view.findViewById(R.id.stopplaceName);
            viewHolder.park_duration= (TextView) view.findViewById(R.id.park_duration);
            viewHolder.spend_money= (TextView) view.findViewById(R.id.spend_money);
            viewHolder.spendTime= (TextView) view.findViewById(R.id.spendTime);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.stopplaceName.setText(list.get(position).getStopPlaceName());
        viewHolder.park_duration.setText(list.get(position).getPeriod());
        viewHolder.spend_money.setText(list.get(position).getMoney().toString()+"元");
        viewHolder.spendTime.setText("        " + list.get(position).getCreateTime());
        return view;
    }
    static class ViewHolder{
        TextView stopplaceName,park_duration,spend_money,spendTime;
    }
}
