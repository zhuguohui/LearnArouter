package com.example.trscommand.load;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:44
 * Desc:
 */
public final class SuccessData<T> extends StateData {
    T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }


    public SuccessData(String opName, T data) {
        super(opName);
        this.data = data;
    }
}
