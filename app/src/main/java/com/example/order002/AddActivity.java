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

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText price;
    private Button ok;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.ed_mealname);
        price = findViewById(R.id.ed_mealprice);

        ok = findViewById(R.id.btn_ok);
        back = findViewById(R.id.btn_back);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = name.getText().toString();
                double newprice = Double.parseDouble(price.getText().toString());

                List<Model.SubModel> xinpin = new ArrayList<>();
                xinpin.add(new Model.SubModel(newname,newprice));
                Toast.makeText(AddActivity.this,"添加菜品成功",Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AddActivity.this,AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }
}