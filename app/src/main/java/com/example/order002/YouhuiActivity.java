package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YouhuiActivity extends AppCompatActivity {

    private EditText max;
    private EditText youhui;
    private Button ok;
    private Button back;
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhui);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        max = findViewById(R.id.ed_max);
        youhui = findViewById(R.id.ed_youhui);
        ok = findViewById(R.id.btn_ok);
        back = findViewById(R.id.btn_back);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();


                double maxp = Double.parseDouble(max.getText().toString());
                double youhuip = Double.parseDouble(youhui.getText().toString());

                values.put("max",maxp);
                values.put("youhui",youhuip);
                db.insert("youhuiData1",null,values);
                db.close();
                Toast.makeText(YouhuiActivity.this,"发放优惠券满"+max.getText().toString()+"减"+youhui.getText().toString()+"成功",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(YouhuiActivity.this,AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}