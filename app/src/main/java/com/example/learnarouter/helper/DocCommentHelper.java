package com.example.learnarouter.helper;

import android.content.Context;
import android.content.Intent;

import com.example.annotation.CommandExecutor;
import com.example.learnarouter.commond.impl.CreateDocCommand;
import com.example.learnarouter.ui.DocCreateActivity;

public class DocCommentHelper {


    @CommandExecutor
    public static void doCreateDocCommand(CreateDocCommand command, Context context){
        Intent intent=new Intent(context, DocCreateActivity.class);
        intent.putExtra("key_type",command.getDocType());
        context.startActivity(intent);
    }
}
