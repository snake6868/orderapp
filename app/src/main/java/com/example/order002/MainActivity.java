package com.example.order002;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_create;
    private Button btn_login;
    private EditText edit_password;
    private EditText edit_user;
    private RadioGroup radioGroup;
    private RadioButton rdb_user;
    private RadioButton rdb_admin;
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        radioGroup = findViewById(R.id.radioGroup);
        rdb_user = findViewById(R.id.radioButtonuser);
        rdb_admin = findViewById(R.id.radioButtonadmin);

        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdb_user = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                if(rdb_user.getText().toString().equals("用户")) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
                RadioButton rdb_admin = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                if(rdb_admin.getText().toString().equals("管理员")){
                    Toast.makeText(MainActivity.this,"注册管理员权限请联系工作人员",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdb_user = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                if(rdb_user.getText().toString().equals("用户"))
                    login();
                RadioButton rdb_admin = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                if(rdb_admin.getText().toString().equals("管理员")) {
                    loginadmin();
                }
            }
        });
    }

    //点击登录按钮
    public void login() {

        edit_user=(EditText)findViewById(R.id.ed_user);
        edit_password=(EditText)findViewById(R.id.ed_password);

        String userName=edit_user.getText().toString();
        String passWord=edit_password.getText().toString();

        if (login(userName,passWord)) {
            Toast.makeText(MainActivity.this, "用户登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,OrderActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(MainActivity.this, "用户登陆失败", Toast.LENGTH_SHORT).show();
        }
    }

    //验证登录
    public boolean login(String username,String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData1 where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //管理员登录
    public void loginadmin() {

        edit_user=(EditText)findViewById(R.id.ed_user);
        edit_password=(EditText)findViewById(R.id.ed_password);

        String userName=edit_user.getText().toString();
        String passWord=edit_password.getText().toString();

        if (loginadmin(userName,passWord)) {
            Toast.makeText(MainActivity.this, "管理员登陆成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "管理员登陆失败", Toast.LENGTH_SHORT).show();
        }
    }

    //验证登录
    public boolean loginadmin(String username,String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from adminData1 where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

}