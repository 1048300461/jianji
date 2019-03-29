package com.example.jianji.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jianji.Chat.Chat_friend;
import com.example.jianji.DB.Myuser;
import com.example.jianji.R;
import com.example.jianji.adapter.FriendAdapter;
import com.example.jianji.model.BaseModel;
import com.example.jianji.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class chatActivity extends AppCompatActivity  {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Chat_friend> friendList = new ArrayList<>();
    private FriendAdapter adapter;

    //测试按钮
    private Button addFriend_btn;
    private EditText addFriend_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        addFriend_btn = findViewById(R.id.addFriend_btn);
        addFriend_edit = findViewById(R.id.addFriend_edit);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //加载最新消息
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.chat_recycler_view);
        adapter = new FriendAdapter(friendList);
        recyclerView.setAdapter(adapter);

        addFriend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setRefreshing(true);
                query();
            }
        });

    }

    private void query() {
        String name = addFriend_edit.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(chatActivity.this,"请填写用户名",
                    Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        UserModel.getInstance().queryUsers(name, BaseModel.DEFAULT_LIMIT,
                new FindListener<Myuser>() {
                    @Override
                    public void done(List<Myuser> list, BmobException e) {
                        if (e == null) {
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(chatActivity.this,list.get(0).getUsername(),
                                    Toast.LENGTH_SHORT).show();
                            //adapter.setDatas(list);
                            //adapter.notifyDataSetChanged();
                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                            //adapter.setDatas(null);
                            //adapter.notifyDataSetChanged();
                            //Logger.e(e);
                        }
                    }
                });
    }


}
