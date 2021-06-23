package com.example.learnarouter.commond.impl;

import android.content.Context;

import com.example.learnarouter.commond.base.TRSCommand;
import com.example.trscommand.load.livedata.StateLiveData;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TRSCommandManager {
    private static TRSCommandManager instance=new TRSCommandManager();
    private Map<TRSCommand<?>,StateLiveData<?>> stateLiveDataMap=new HashMap<>();
    private Map<Class,Method> methodMap=new HashMap<>();


    public static TRSCommandManager getInstance() {
        return instance;
    }

    public <T>  StateLiveData<T> execute(TRSCommand<T> trsCommand, Context context){
        StateLiveData<T> data=getLiveData(trsCommand);
        Method method = methodMap.get(trsCommand.getClass());
        if(method==null){
            //通过反射查找
            String executorClassName = "com.trs.command."+trsCommand.getClass().getSimpleName() + "Executor";
            try {
                method = Class.forName(executorClassName).getMethod("execute", trsCommand.getClass(), Context.class);
                methodMap.put(trsCommand.getClass(),method);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(method==null){
            data.onError(trsCommand.getDesc(),"没有找到命令处理器",null);
            return data;
        }
        try {
            method.invoke(null, trsCommand,context);
        } catch (Exception e) {
            e.printStackTrace();
            data.onError(trsCommand.getDesc(),"内部错误:"+e.getMessage(),null);
        }
        return data;
    }

    public  <T>  StateLiveData<T> getLiveData(TRSCommand<T> trsCommand){
        StateLiveData<?> data = stateLiveDataMap.get(trsCommand);
        if(data==null){
            data=new StateLiveData<>();
            stateLiveDataMap.put(trsCommand,data);
        }
        return (StateLiveData<T>) data;
    }

}
