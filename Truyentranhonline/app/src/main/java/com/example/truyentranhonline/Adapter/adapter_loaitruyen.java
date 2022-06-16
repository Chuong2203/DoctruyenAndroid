package com.example.truyentranhonline.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.model_loaitruyen;

import java.util.ArrayList;

public class adapter_loaitruyen extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<model_loaitruyen>arrayList;
    private int imageview;

    public adapter_loaitruyen(Context context, int layout,int imageview, ArrayList<model_loaitruyen> arrayList) {
        this.context = context;
        this.layout = layout;
        this.imageview=imageview;
        this.arrayList = arrayList;
    }

    //tar về số lượng item hiển thị
    @Override
    public int getCount() {
        return arrayList.size();
    }

    //lấy ra object vị trí position
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //trả về view item cho listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context,layout,null);
        TextView txttentruyen=convertView.findViewById(R.id.txttentruyen);
        ImageView imageView=convertView.findViewById(R.id.imageview);


        imageView.setImageResource(imageview);
        txttentruyen.setText(arrayList.get(position).getTentruyen());
        return convertView;
    }
}
