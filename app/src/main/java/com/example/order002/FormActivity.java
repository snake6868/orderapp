package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    ListView formlist;
    TextView tv_sum;
    TextView tv_username;
    TextView tv_youhui;
    RightAdapter mRightAdapter;
    List<Model> mModelList;
    List<Model.SubModel> mSubModelList;
    FormAdapter formAdapter;
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


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FormActivity.this,OrderActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }


}