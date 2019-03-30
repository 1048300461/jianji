package com.example.jianji.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jianji.R;
import com.example.jianji.Utils.MD5Utils;

import cn.bmob.v3.Bmob;

public class MainMenuActivity extends AppCompatActivity {

    private Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        create = findViewById(R.id.create_btn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
