package com.cdut.cn.cloundbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddbooksActivity extends AppCompatActivity implements View.OnClickListener {
    //组件定义
    private EditText etStudentid;
    private EditText etStudentname;
    private EditText etMajoy;
    private EditText etBooknum;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        etStudentid=(EditText)findViewById(R.id.et_studentid);
        etStudentname = (EditText) findViewById(R.id.et_studentname);
        etMajoy = (EditText) findViewById(R.id.et_majoy);
        etBooknum = (EditText) findViewById(R.id.et_booknum);

        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String studentid=etStudentid.getText().toString().trim();
        String studentname = etStudentname.getText().toString().trim();
        String majoy = etMajoy.getText().toString().trim();
        String booknum = etBooknum.getText().toString();


        //检验信息是否正确
        if (TextUtils.isEmpty(studentid)) {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(studentname)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(majoy)) {
            Toast.makeText(this, "请输入专业", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(booknum)) {
            Toast.makeText(this, "请输入书条码", Toast.LENGTH_SHORT).show();
            return;
        }

        //添加借书信息
        Book o =new Book();
        o.studentid= studentid;
        o.studentname = studentname;
        o.majoy = majoy;
        o.booknum= booknum;

        //创建数据库访问对象
        BooksDAO dao = new BooksDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addBooks(o);

        if (result > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
        //关闭活动
        finish();

    }
}