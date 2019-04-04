package com.example.jianji.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jianji.R;
import com.example.jianji.Utils.MD5Utils;
import com.example.jianji.adapter.ItemRemovedListener;
import com.example.jianji.adapter.SwipeCardLayoutManager;
import com.example.jianji.adapter.SwipeCardRecyclerView;
import com.example.jianji.adapter.SwipeCardRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentNew extends Fragment {

    private SwipeCardRecyclerView mRecyclerView;
    private SwipeCardRecyclerViewAdapter mAdapter;

    public MyFragmentNew(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_content_new, container, false);

        mRecyclerView = (SwipeCardRecyclerView) view.findViewById(R.id.swipeCardRecyclerView);
        mRecyclerView.setLayoutManager(new SwipeCardLayoutManager());
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        mAdapter = new SwipeCardRecyclerViewAdapter(getContext(), list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRemovedListener(new ItemRemovedListener() {
            @Override
            public void onRightRemoved() {
                Toast.makeText(getContext(), list.get(list.size() - 1) + " was right removed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftRemoved() {
                Toast.makeText(getContext(), list.get(list.size() - 1) + " was left removed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
