package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private LinearLayout add;
    private LinearLayout delete;
    private LinearLayout youhui;
    private LinearLayout dingdan;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add = findViewById(R.id.Lin_add);
        delete = findViewById(R.id.Lin_delete);
        youhui = findViewById(R.id.Lin_youhui);
        dingdan = findViewById(R.id.Lin_dingdan);
        back = findViewById(R.id.admin_back);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this,AddActivity.class);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this,DeleteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this,YouhuiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this,DingdanActivity.class);
                List<Form> form = (List<Form>) getIntent().getSerializableExtra("formlist2");
                intent.putExtra("formlist2",(Serializable)form);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}