package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class DingdanActivity extends AppCompatActivity {
    ListView formlist;
    TextView tv_sum;
    TextView tv_username;
    TextView tv_youhui;

    FormAdapter formAdapter;
    Button receive;
    Button back;
    private MyDBHelper dbHelper;
    private ImageView youhuiquan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);


        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        formlist=findViewById(R.id.list_form);
        tv_username=findViewById(R.id.tv_username);
        tv_sum=findViewById(R.id.tv_sum);
        tv_youhui=findViewById(R.id.form_youhui);
        youhuiquan=findViewById(R.id.form_youhuiquan);

        List<Form> form = (List<Form>) getIntent().getSerializableExtra("formlist2");
        formAdapter = new FormAdapter(this,form);
        formlist.setAdapter(formAdapter);

        SQLiteDatabase db= dbHelper.getReadableDatabase();

        Cursor cursor = db.query("formData1", null, null, null, null, null, null);
        double finalsum=0;
        if(cursor.moveToLast())
        {
            tv_username.setText(cursor.getString(cursor.getColumnIndex("user"))+"用户");
            tv_sum.setText("¥"+cursor.getDouble(cursor.getColumnIndex("sum")));
            finalsum=cursor.getDouble(cursor.getColumnIndex("sum"));
        }

        double maxp=0,youhuip=0;

        Cursor cursor1 = db.query("youhuiData1",null,null,null,null,null,null);
        if(cursor1.moveToLast())
        {
            maxp=cursor1.getDouble(cursor1.getColumnIndex("max"));
            youhuip=cursor1.getDouble(cursor1.getColumnIndex("youhui"));
            if(finalsum+youhuip>=maxp)
                tv_youhui.setText("已应用满"+maxp+"减"+youhuip+"优惠");
        }



        receive = findViewById(R.id.btn_receive);
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DingdanActivity.this,"确认订单成功",Toast.LENGTH_SHORT).show();
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DingdanActivity.this,AdminActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }
}










