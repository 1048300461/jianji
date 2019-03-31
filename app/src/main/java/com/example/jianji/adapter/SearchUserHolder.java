package com.example.jianji.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jianji.Activities.UserInfoActivity;
import com.example.jianji.DB.Myuser;
import com.example.jianji.R;

import butterknife.BindView;

public class SearchUserHolder extends BaseViewHolder{


    public ImageView avatar;

    public TextView name;

    public Button btn_add;

    public SearchUserHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_search_user,onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final Myuser user =(Myuser)o;
        //问题
        Glide.with(getContext()).load(user.getAvatar()).load(avatar);

        name.setText(user.getUsername());
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//查看个人详情
                Bundle bundle = new Bundle();
                bundle.putSerializable("u", user);
                startActivity(UserInfoActivity.class,bundle);
            }
        });
    }
}
