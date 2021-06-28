package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private EditText name;
    private Button ok;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        name = findViewById(R.id.ed_mealname);
        ok = findViewById(R.id.btn_ok);
        back = findViewById(R.id.btn_back);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("测试2"))
                    Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DeleteActivity.this,"该菜名不存在",Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DeleteActivity.this,AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}