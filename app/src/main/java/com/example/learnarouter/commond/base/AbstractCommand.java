package com.example.learnarouter.commond.base;

import android.content.Context;

import com.example.learnarouter.commond.impl.TRSCommandManager;
import com.example.trscommand.load.livedata.StateLiveData;

public class AbstractCommand<T> implements TRSCommand<T> {
    @Override
    public String getDesc() {
        throw new RuntimeException("没有实现该方法");
    }



    @Override
    public StateLiveData<T> execute(Context context) {
        return TRSCommandManager.getInstance().execute(this,context);
    }

    @Override
    public StateLiveData<T> getStateData() {
        return TRSCommandManager.getInstance().getLiveData(this);
    }




}
