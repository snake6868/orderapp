package com.example.order002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    ListView formlist;
    TextView tv_sum;
    TextView tv_username;
    RightAdapter mRightAdapter;
    List<Model> mModelList;
    List<Model.SubModel> mSubModelList;
    FormAdapter formAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        formlist=findViewById(R.id.list_form);
        tv_username=findViewById(R.id.tv_username);
        tv_sum=findViewById(R.id.tv_sum);
        List<Form> form = (List<Form>) getIntent().getSerializableExtra("formlist1");
        Log.d("ckfckf",form.get(0).getFname());
        formAdapter = new FormAdapter(this,form);
        formlist.setAdapter(formAdapter);
        Intent intent =getIntent();
        String sum = intent.getStringExtra("sum");
        String username = intent.getStringExtra("username");
        tv_sum.setText("¥"+sum);
        tv_username.setText(username+"");


    }


}