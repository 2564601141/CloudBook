package com.cdut.cn.cloundbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    /**
     * 声明一个AndroidSDK自带的数据库变量db
     */
    private SQLiteDatabase db;

    MyDBHelper(Context context){
        super(context,"db_book.db",null,1);
        db = getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "email TEXT," +
                "phonenum TEXT)"
        );
        db.execSQL("create table tb_Books(studentid char(10)primary key,studentname varchar(20),majoy varchar(20),booknum varchar(20))");
        db.execSQL("insert into tb_Books(studentid,studentname,majoy,booknum)Values('201901','路人甲','计科','123456')");
        db.execSQL("insert into tb_Books(studentid,studentname,majoy,booknum)Values('201902','路人乙','计科','212314')");
        db.execSQL("insert into tb_Books(studentid,studentname,majoy,booknum)Values('201903','路人丙','计科','123356')");
    }
    //版本适应
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    void add(String name, String password,String email,String phonenum){
        db.execSQL("INSERT INTO user (name,password,email,phonenum) VALUES(?,?,?,?)",new Object[]{name,password,email,phonenum});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }
    ArrayList<User> getAllData(){
        ArrayList<User> list = new ArrayList<User>();
        @SuppressLint("Recycle") Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phonenum = cursor.getString(cursor.getColumnIndex("phonenum"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            list.add(new User(name,password,email,phonenum));
        }
        return list;
    }
}

