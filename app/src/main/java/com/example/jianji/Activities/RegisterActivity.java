package com.example.jianji.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jianji.DB.Myuser;
import com.example.jianji.R;
import com.example.jianji.Utils.MD5Utils;
import com.example.jianji.Utils.PhoneFormatCheckUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    EditText input_tele,input_name,input_password,input_telever;
    private ImageButton backBtn;
    private Button create_btn,sendver_btn;
    //昵称，电话，密码
    String name,tele,password,telever;
    private boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        input_name = (EditText) findViewById(R.id.name);
        input_tele = (EditText) findViewById(R.id.tele_number);
        input_password = (EditText) findViewById(R.id.password);
        input_telever = (EditText) findViewById(R.id.tele_ver);
        create_btn = (Button) findViewById(R.id.create_btn);
        sendver_btn = (Button) findViewById(R.id.sendver_btn);

        backBtn = (ImageButton) findViewById(R.id.back);
        //设置成输入显示数字键盘
        input_tele.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        input_telever.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        //放回上一个页面
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //发送验证码
        sendver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查找电话号码是否重复
                BmobQuery<Myuser> query = new BmobQuery<Myuser>();
                query.addWhereEqualTo("mobilePhoneNumber",input_tele.getText().toString().trim());
                query.findObjects(new FindListener<Myuser>() {
                    @Override
                    public void done(List<Myuser> list, BmobException e) {
                        if(e == null){
                            if(list.size() != 0){
                                Toast.makeText(RegisterActivity.this,"该手机号已注册",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                BmobSMS.requestSMSCode(input_tele.getText().toString().trim(),
                                        "jianji", new QueryListener<Integer>() {
                                            @Override
                                            public void done(Integer smsId, BmobException e) {
                                                if(e == null){
                                                    //用于查询本次短信发送详情
                                                    Log.i("smile","短信id：" + smsId);
                                                    Toast.makeText(RegisterActivity.this,"发送成功",
                                                            Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Log.i("smile", e.getErrorCode() + "-" + e.getMessage());
                                                }
                                            }
                                        });
                            }

                        }else{
                            Log.i("smile",e.getMessage());
                        }
                    }
                });

            }
        });
        //注册用户
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去掉首位空格，防止不必要的错误
                name = input_name.getText().toString().trim();
                password = input_password.getText().toString();
                tele = input_tele.getText().toString().trim();
                telever = input_telever.getText().toString().trim();

                result = PhoneFormatCheckUtils.isPhoneLegal(tele);

                if(result && name.length() > 0 && password.length() >= 6 && telever.length() > 0){
                    final Myuser user = new Myuser();

                    //对密码进行加密
                    password = MD5Utils.md5Password(password);
                    //添加用户信息
                    user.setMobilePhoneNumber(tele);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.signOrLogin(telever, new SaveListener<Myuser>() {
                        @Override
                        public void done(Myuser myuser, BmobException e) {
                            if(e == null){
                                Toast.makeText(RegisterActivity.this,"注册成功",
                                        Toast.LENGTH_SHORT).show();
                                Log.i("smile",user.getUsername() + "-" + user.getObjectId());
                            }else{
                                Toast.makeText(RegisterActivity.this,"注册失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    //不符合规范
                    if(result == false){
                        Toast.makeText(RegisterActivity.this,"手机号格式错误",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(name.length() == 0){
                        Toast.makeText(RegisterActivity.this,"昵称不能为空",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(password.length() < 6){
                        Toast.makeText(RegisterActivity.this,"密码长度需大于6",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(telever.length() == 0){
                        Toast.makeText(RegisterActivity.this,"请输入验证码",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


            }
        });
    }
}
