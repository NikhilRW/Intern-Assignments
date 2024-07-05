package com.example.bluetoothconnector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String>{
    ArrayList<String> arr;
    public  CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> arr) {
        super(context, resource,arr);
        this.arr=arr;
    }
    @Nullable
    @Override
    public String getItem(int position) {
        return arr.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.bluetooth_list_layout,parent,false);
        TextView textView=convertView.findViewById(R.id.textView3);
        Log.d("nik", "getView: "+arr.get(position));
        textView.setText(arr.get(position));
        return  convertView;
    }
}
