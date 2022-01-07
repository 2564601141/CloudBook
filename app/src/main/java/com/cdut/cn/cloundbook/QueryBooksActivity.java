package com.cdut.cn.cloundbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.Map;

public class QueryBooksActivity extends AppCompatActivity {
    //定义组件
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_books);
        setTitle("查看记录");
        //初始化界面
        initView();
    }

    private void initView() {
        //建立数据库访问对象
        BooksDAO dao=new BooksDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        List<Map<String,Object>> mOrderData=dao.getAllbooks();
        //获取组件
        listView=(ListView)findViewById(R.id.lst_orders);
        //定义数据源
        String[] from={"studentid","studentname","majoy","booknum"};
        //定义布局控件ID
        int[] to={R.id.tv_lst_studentid,R.id.tv_lst_studentname,R.id.tv_lst_majoy,R.id.tv_lst_booknum};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryBooksActivity.this,mOrderData,R.layout.item_list,from,to);
        //添加并显示
        listView.setAdapter(listItemAdapter);
        //关闭数据库
        dao.close();
    }
}
