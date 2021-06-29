package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    ListView formlist;
    TextView tv_sum;
    TextView tv_username;
    TextView tv_youhui;
    FormAdapter formAdapter;
    Button submit;
    Button back;
    private MyDBHelper dbHelper;
    private ImageView youhuiquan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        formlist=findViewById(R.id.list_form);
        tv_username=findViewById(R.id.tv_username);
        tv_sum=findViewById(R.id.tv_sum);
        tv_youhui=findViewById(R.id.form_youhui);
        youhuiquan=findViewById(R.id.form_youhuiquan);

        List<Form> form = (List<Form>) getIntent().getSerializableExtra("formlist1");
        formAdapter = new FormAdapter(this,form);
        formlist.setAdapter(formAdapter);
        Intent intent =getIntent();
        String sum = intent.getStringExtra("sum");
        String username = intent.getStringExtra("username");

        double finalsum = Double.parseDouble(sum);

        SQLiteDatabase db= dbHelper.getReadableDatabase();

        Cursor cursor = db.query("youhuiData1", null, null, null, null, null, null);
        double maxp=0,youhuip=0;

        if(cursor.moveToLast())
        {
            maxp=cursor.getDouble(cursor.getColumnIndex("max"));
            youhuip=cursor.getDouble(cursor.getColumnIndex("youhui"));
            tv_youhui.setText("满"+maxp+"减"+youhuip);
        }

        if(finalsum>=maxp)
        {
            finalsum-=youhuip;
            youhuiquan.setAlpha(1f);
        }

        tv_sum.setText("¥"+finalsum);
        tv_username.setText(username+"用户");

        submit=findViewById(R.id.btn_submit);
        double finalSum = finalsum;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();

                values.put("user",username);
                values.put("sum", finalSum);
                db.insert("formData1",null,values);
                db.close();
                Toast.makeText(FormActivity.this,"订单提交成功",Toast.LENGTH_SHORT).show();


            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("formlist2",(Serializable)form);
                intent.setClass(FormActivity.this,OrderActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }


}