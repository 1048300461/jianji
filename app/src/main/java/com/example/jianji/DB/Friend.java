package com.example.jianji.DB;

import cn.bmob.v3.BmobObject;

public class Friend extends BmobObject {
    private Myuser user;
    private Myuser friendUser;

    //拼音
    private transient String pinyin;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Myuser getUser() {
        return user;
    }

    public void setUser(Myuser user) {
        this.user = user;
    }

    public Myuser getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(Myuser friendUser) {
        this.friendUser = friendUser;
    }
}
