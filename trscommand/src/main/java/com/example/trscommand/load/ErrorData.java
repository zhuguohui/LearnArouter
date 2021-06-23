package com.example.trscommand.load;


import com.example.trscommand.load.api.RetryListener;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:42
 * Desc:用来标记数据出错
 */
public final class ErrorData extends StateData {
    Throwable throwable;

    private boolean useThrowable;
    private String msg;
    private RetryListener retryListener;

    public ErrorData(String opName, Throwable throwable, RetryListener retryListener) {
        super(opName);
        this.throwable = throwable;
        useThrowable = true;
        this.retryListener=retryListener;
    }

    public ErrorData(String opName, String msg, RetryListener retryListener)
    {   super(opName);
        this.msg = msg;
        this.retryListener=retryListener;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }


    public RetryListener getRetryListener() {
        return retryListener;
    }

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public String getMsg() {
        if (useThrowable && throwable != null) {
            return throwable.getMessage();
        } else {
            return msg == null ? "未知错误" : msg;
        }
    }
}
