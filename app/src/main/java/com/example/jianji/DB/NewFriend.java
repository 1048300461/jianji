package com.example.jianji.DB;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NewFriend implements java.io.Serializable{
    static  final  long serialVersionUID =  42L;
    //用户的uid
    private String uid;
    //留言消息
    private String msg;
    //用户名
    private String name;
    //头像
    private String avatar;
    //状态：未读，已读，已添加，已拒绝等
    private Integer status;
    //请求时间
    private Long time;
    @Generated(hash = 1550151525)
    public NewFriend(String uid, String msg, String name, String avatar,
            Integer status, Long time) {
        this.uid = uid;
        this.msg = msg;
        this.name = name;
        this.avatar = avatar;
        this.status = status;
        this.time = time;
    }
    @Generated(hash = 163516704)
    public NewFriend() {
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    

}
