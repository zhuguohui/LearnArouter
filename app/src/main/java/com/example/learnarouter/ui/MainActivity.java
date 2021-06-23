package com.example.learnarouter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.learnarouter.R;
import com.example.learnarouter.bean.DocItem;
import com.example.learnarouter.commond.impl.CreateDocCommand;
import com.example.trscommand.load.impl.ToastStateView;
import com.example.trscommand.load.observer.StateDataObserver;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_create).setOnClickListener(v->{
            new CreateDocCommand("图集")
                    .execute(this)
                    .observeState(this,new StateDataObserver<DocItem>(new ToastStateView<>(this)));
        });

    }
}