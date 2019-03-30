package com.example.jianji;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

public class MyMessageHandler extends BmobIMMessageHandler {
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        //在线消息
        super.onMessageReceive(messageEvent);
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        //离线消息
        super.onOfflineReceive(offlineMessageEvent);
    }
}
