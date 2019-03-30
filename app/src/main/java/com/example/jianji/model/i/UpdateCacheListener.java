package com.example.jianji.model.i;

import com.example.jianji.DB.Myuser;

import cn.bmob.newim.listener.BmobListener1;
import cn.bmob.v3.exception.BmobException;

public abstract class UpdateCacheListener extends BmobListener1<Myuser> {
    public abstract void done(BmobException e);

    @Override
    protected void postDone(Myuser o, BmobException e) {
        done(e);
    }
}
