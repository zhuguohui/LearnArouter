package com.example.trscommand.load.impl;

import android.content.Context;
import android.widget.Toast;

import com.example.trscommand.load.api.IStateView;
import com.example.trscommand.load.api.RetryListener;

public class ToastStateView<T> implements IStateView<T> {
    Context context;

    public ToastStateView(Context context) {
        this.context = context;
    }

    @Override
    public void onError(String msg, RetryListener retryListener) {
        Toast.makeText(context,"失败:"+msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String opName, T t) {
        Toast.makeText(context,opName+"成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoading(String opName, float progress) {
        Toast.makeText(context,"正在"+opName,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmpty() {
        Toast.makeText(context,"数据为空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetError(RetryListener retryListener) {
        Toast.makeText(context,"网络错误",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRelease() {

    }
}
