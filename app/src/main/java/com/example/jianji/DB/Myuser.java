package com.example.jianji.DB;

import cn.bmob.v3.BmobUser;

public class Myuser extends BmobUser {

    private Boolean sex;
    private String avatar;
    private Integer age;
    public Myuser(){}

    public Myuser(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
