package com.example.jianji.model.i;

import com.example.jianji.DB.Myuser;

import cn.bmob.newim.listener.BmobListener1;
import cn.bmob.v3.exception.BmobException;

public abstract class QueryUserListener extends BmobListener1<Myuser> {

    public abstract void done(Myuser s, BmobException e);
    @Override
    protected void postDone(Myuser o, BmobException e) {
        done(o, e);
    }
}
