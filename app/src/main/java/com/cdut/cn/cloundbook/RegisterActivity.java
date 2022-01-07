package com.cdut.cn.cloundbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_rgsName, et_rgsEmail, et_rgsPhoneNum, et_rgsPsw1, et_rgsPsw2;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//禁止横屏
        setContentView(R.layout.activity_register);
        setTitle("用户注册");//顶部标题改成用户注册

        initView();//初始化界面
        myDBHelper = new MyDBHelper(this);
    }

    private void initView() {
        et_rgsName = findViewById(R.id.et_rgsName);
        et_rgsEmail = findViewById(R.id.et_rgsEmail);
        et_rgsPhoneNum = findViewById(R.id.et_rgsPhoneNum);
        et_rgsPsw1 = findViewById(R.id.et_rgsPsw1);
        et_rgsPsw2 = findViewById(R.id.et_rgsPsw2);

        Button btn_register = findViewById(R.id.btn_rgs);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://返回登录界面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_rgs://注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = et_rgsName.getText().toString().trim();
                String password1 = et_rgsPsw1.getText().toString().trim();
                String password2 = et_rgsPsw2.getText().toString().trim();
                String email = et_rgsEmail.getText().toString().trim();
                String phonenum = et_rgsPhoneNum.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
                    //判断两次密码是否一致
                    if (password1.equals(password2)) {
                        //将用户名和密码加入到数据库中
                        myDBHelper.add(username, password2, email, phonenum);
                        Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        finish();
                        Toast.makeText(this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "两次密码不一致,注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "注册信息不完善,注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
