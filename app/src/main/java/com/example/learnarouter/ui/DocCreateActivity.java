package com.example.learnarouter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.learnarouter.R;

public class DocCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_create_activty);
        String type= getIntent().getStringExtra("key_type");
        TextView textView=findViewById(R.id.tv_content);
        textView.setText("创建"+type+"稿件");
    }
}