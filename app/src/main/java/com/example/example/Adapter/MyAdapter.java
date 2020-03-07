package com.example.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.example.item.Activity_item;
import com.example.example.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<Activity_item> mList;


    public MyAdapter(Context context, ArrayList<Activity_item> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Activity_item getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, mList.get(position).getActivity_Class());
                context.startActivity(intent);
            }
        });

        TextView activity_name = view.findViewById(R.id.activty_name);
        activity_name.setText(mList.get(position).getActivty_Name());
        return view;
    }
}
