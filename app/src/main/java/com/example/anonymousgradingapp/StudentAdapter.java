package com.example.anonymousgradingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private String[] names;
    private Bitmap[] images;
    private LayoutInflater inflater;
    public StudentAdapter(Context context, String[] name, Bitmap[] barcode){
        this.context = context;
        this.names = name;
        this.images = barcode;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.studentandbarcode, null);
        TextView text = (TextView) convertView.findViewById(R.id.studentName);
        ImageView image = (ImageView) convertView.findViewById(R.id.barcodeImage);
        text.setText(names[position]);
        image.setImageBitmap(images[position]);
        //image.setImageResource(images[position]);
        return convertView;
    }
}
