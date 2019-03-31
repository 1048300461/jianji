package com.example.jianji.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
/**
*   登入界面
 */
public class Sing_InActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private ImageButton backBtn;
    private EditText input_tele, input_password;
    String tele, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__in);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        loginBtn = (Button) findViewById(R.id.login_btn);
        input_tele = (EditText) findViewById(R.id.user_tel);
        input_password = (EditText) findViewById(R.id.password);
        //设置成数字键盘
        input_tele.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        backBtn = (ImageButton) findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent(Sing_InActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                tele = input_tele.getText().toString().trim();
                password = input_password.getText().toString().trim();
                //检测用户手机号是否合法
                Boolean result = PhoneFormatCheckUtils.isPhoneLegal(tele);
                if (!result) {
                    Toast.makeText(Sing_InActivity.this, "手机号不合法",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //密码输入框为空
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Sing_InActivity.this, "请输入密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //将输入的密码也进行md5加密来判断密码是否与手机号匹配
                password = MD5Utils.md5Password(password);
                Myuser.loginByAccount(tele, password, new LogInListener<Myuser>() {
                    @Override
                    public void done(Myuser myuser, BmobException e) {
                        if (myuser != null) {
                            Intent intent = new Intent(Sing_InActivity.this, FriendActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Sing_InActivity.this, "用户名与密码不匹配",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
