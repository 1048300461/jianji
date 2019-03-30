package com.example.jianji.Chat;

import com.example.jianji.DB.Myuser;

import java.util.List;

public class Chat_friend {

    private String fiendName;

    private String latestMes;

    private int avatar;

    public Chat_friend(String fiendName, String latestMes, int avatar){
        this.fiendName = fiendName;
        this.latestMes = latestMes;
        this.avatar = avatar;
    }



    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getFiendName() {
        return fiendName;
    }

    public void setFiendName(String fiendName) {
        this.fiendName = fiendName;
    }

    public String getLatestMes() {
        return latestMes;
    }

    public void setLatestMes(String latestMes) {
        this.latestMes = latestMes;
    }
}
