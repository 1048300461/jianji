package com.example.jianji.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jianji.R;
import com.example.jianji.Utils.MD5Utils;

public class MyFragmentMe extends Fragment {

    public MyFragmentMe(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_content_me, container, false);
        return view;
    }
}
