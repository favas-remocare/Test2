package com.favas.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.favas.myapplication.R;

import java.util.List;
import java.util.Map;

/**
 * Created by timaimee on 2016/12/21.
 */
public class GridAdatper extends BaseAdapter {
    private List<Map<String, String>> mGridData;
    LayoutInflater mLayoutInflater;


    public GridAdatper(Context context, List<Map<String, String>> itemData) {
        this.mGridData = itemData;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mGridData.size();
    }

    @Override
    public Object getItem(int i) {

        return mGridData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GridHold mGridHold;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.item_grid, null);
            mGridHold = new GridHold();
            mGridHold.mButton = (TextView) convertView.findViewById(R.id.gridbutton);
            convertView.setTag(mGridHold);
        } else {
            mGridHold = (GridHold) convertView.getTag();
        }
        Map<String, String> map = mGridData.get(i);
        mGridHold.mButton.setText(map.get("str"));

        if(map.get("str").trim().equals("Measuring heart rate-start")  || map.get("str").trim().equals("Measuring heart rate-end")  || map.get("str").trim().contains("Measuring blood pressure") || map.get("str").trim().contains("Device password") || map.get("str").trim().contains("Blood Oxygen") || map.get("str").trim().contains("Current step count-read")  )
        {
            mGridHold.mButton.setBackgroundResource(R.color.colorAccent);
        }
        else
        {
            mGridHold.mButton.setBackgroundResource(R.color.Dark_Grey);

        }


        return convertView;
    }


    static class GridHold {
        TextView mButton;
    }
}
