package com.example.jianji.Activities;

import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jianji.DB.AddFriendMessage;
import com.example.jianji.DB.Myuser;
import com.example.jianji.R;
import com.example.jianji.Utils.ParentWithNaviActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class UserInfoActivity extends ParentWithNaviActivity {

    @BindView(R.id.info_avatar)
    ImageView info_avatar;
    @BindView(R.id.info_name)
    TextView info_name;


    @BindView(R.id.add_btn)
    Button add_btn;
    @BindView(R.id.share_btn)
    Button share_btn;

    Myuser user;
    BmobIMUserInfo info;

    @Override
    protected String title() {
        return "个人资料";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initNaviView();
        //获取传来的用户id
        user = (Myuser) getBundle().getSerializable("u");
        //如果传入的是自己，设置不显示两个按钮
        if(user.getObjectId().equals(getCurrentUid())){
            add_btn.setVisibility(View.GONE);
            share_btn.setVisibility(View.GONE);
        }else {
            add_btn.setVisibility(View.VISIBLE);
            share_btn.setVisibility(View.VISIBLE);
        }
        //构造聊天方的用户信息:传入用户id,用户名和用户头像三个参数
        info = new BmobIMUserInfo(user.getObjectId(),user.getUsername(),user.getAvatar());
        Glide.with(this).load(user.getAvatar()).into(info_avatar);
        info_name.setText(user.getUsername());

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddFriendMessage();
            }
        });
    }

    /**
     * 发送添加好友的请求
     */
    private void sendAddFriendMessage(){
        //启动一个会话，如果isTransient设置为true,则不会创建在本地会话表中创建记录，
        //设置isTransient设置为false,则会在本地数据库的会话列表中先创建
        // （如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
        AddFriendMessage msg = new AddFriendMessage();
        Myuser currentUser = BmobUser.getCurrentUser(Myuser.class);
        //给对方的一个留言信息
        msg.setContent("很高兴认识你，可以加个好友吗?");
        Map<String,Object> map =new HashMap<>();
        //发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
        map.put("name", currentUser.getUsername());
        //发送者的头像
        map.put("avatar",currentUser.getAvatar());
        //发送者的uid
        map.put("uid",currentUser.getObjectId());
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {

                if (e == null) {
                    //发送成功
                    toast("好友请求发送成功，等待验证");
                } else {//发送失败
                    toast("发送失败:" + e.getMessage());
                }
            }
        });
    }
}
