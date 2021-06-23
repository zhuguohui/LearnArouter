package com.example.learnarouter.commond.base;

import android.content.Context;

import com.example.trscommand.load.livedata.StateLiveData;

/**
 * 用于构建
 */
public interface  TRSCommand<T> {

    /**
     * 获取描述
     * @return
     */
    String getDesc();

    /**
     * 执行命令
     * @return
     */
    StateLiveData<T> execute(Context context);

    StateLiveData<T> getStateData();



}
