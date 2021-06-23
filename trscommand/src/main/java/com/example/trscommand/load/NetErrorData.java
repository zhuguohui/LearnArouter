package com.example.trscommand.load;


import com.example.trscommand.load.api.RetryListener;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:44
 * Desc: 标记网络错误
 */
public final class NetErrorData extends StateData {

    RetryListener retryListener;
    public NetErrorData(String opName, RetryListener retryListener) {
      super(opName);
      this.retryListener=retryListener;
    }

    public RetryListener getRetryListener() {
        return retryListener;
    }
}
