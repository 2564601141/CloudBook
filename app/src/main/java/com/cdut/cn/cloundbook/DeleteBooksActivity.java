package com.cdut.cn.cloundbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteBooksActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText etStudentid;
    private Button btnSearch;
    private EditText etStudentname;
    private EditText etMajoy;
    private EditText etBooknum;

    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_books);
        initView();
    }

    private void initView() {
        etStudentid=(EditText) findViewById(R.id.et_studentid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etStudentname=(EditText)findViewById(R.id.et_studentname);
        etMajoy=(EditText)findViewById(R.id.et_majoy);
        etBooknum=(EditText)findViewById(R.id.et_booknum);

        btnDelete= (Button) findViewById(R.id.btn_delete);
        //设置按钮的点击事件
        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnDelete.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_delete:    //删除操作
                deleteOrder();
                break;
        }
    }
    //查找借书信息
    private void searchOrder() {
        String studentid=etStudentid.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        BooksDAO dao=new BooksDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Book o=dao.getBooks(studentid);
        //将数据填入控件
        etStudentname.setText(o.studentname);
        etMajoy.setText(o.majoy);
        etBooknum.setText(o.booknum);

        //关闭数据库
        dao.close();
    }
    //删除借书信息
    private void deleteOrder() {
        Book o=new Book();
        o.studentid=etStudentid.getText().toString().trim();
        //创建数据库访问对象
        BooksDAO dao=new BooksDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        int result= dao.deletBooks(o);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
