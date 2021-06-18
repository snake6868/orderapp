package com.example.order002;



import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {

    private Button btn_ok;
    private Button btn_back;
    private EditText edit_password;
    private EditText edit_user;
    private EditText edit_repassword;
    private RadioGroup radioGroup;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void create(){
        edit_user=(EditText)findViewById(R.id.ed_user);
        edit_password=(EditText)findViewById(R.id.ed_password);
        edit_repassword=(EditText)findViewById(R.id.ed_repassword);

        String name =edit_user.getText().toString();
        String password=edit_password.getText().toString();
        String repassword=edit_repassword.getText().toString();

        if(password.equals(repassword)) {

            if (CheckIsDataAlreadyInDBorNot(name)) {
                Toast.makeText(this, "该用户名已被注册，注册失败", Toast.LENGTH_SHORT).show();
            } else {
                if (register(name, password)) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
    //向数据库插入数据
    public boolean register(String username,String password){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",username);
        values.put("password",password);
        db.insert("userData1",null,values);
        db.close();
        return true;
    }
    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String Query = "Select * from userData1 where name =?";
        Cursor cursor = db.rawQuery(Query,new String[] { value });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }
}