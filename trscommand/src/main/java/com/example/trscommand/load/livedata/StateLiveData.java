package com.example.trscommand.load.livedata;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.trscommand.load.EmptyData;
import com.example.trscommand.load.ErrorData;
import com.example.trscommand.load.LoadingData;
import com.example.trscommand.load.NetErrorData;
import com.example.trscommand.load.StateData;
import com.example.trscommand.load.SuccessData;
import com.example.trscommand.load.api.INetResult;
import com.example.trscommand.load.api.RetryListener;
import com.example.trscommand.load.observer.StateDataObserver;


/**
 * Created by zhuguohui
 * Date: 2021/6/8
 * Time: 11:35
 * Desc:对MutableLiveData 进行扩展，使其支持StateData
 */
public class StateLiveData<T> extends MutableLiveData<T> {

    MutableLiveData<StateData> stateLiveData = new MutableLiveData<>();


    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);

    }

    @MainThread
    public void dealINetResult(String opName, INetResult<T> result, RetryListener retryListener) {
        if (result == null) {
            onError(opName, "返回数据为空", retryListener);
            return;
        }
        if (result.isSuccess()) {
            if (result.getData() != null) {
                stateLiveData.setValue(new SuccessData<T>(opName, result.getData()));
                setValue(result.getData());
            } else {
                stateLiveData.setValue(new EmptyData(opName));
                setValue(null);
            }
        } else {
            stateLiveData.setValue(new ErrorData(opName, result.getMessage(), retryListener));

        }
    }

    @MainThread
    public void onNetError(String opName, RetryListener retryListener) {
        stateLiveData.setValue(new NetErrorData(opName,retryListener));
    }

    @MainThread
    public void onError(String opName, String msg, RetryListener retryListener) {
        stateLiveData.setValue(new ErrorData(opName, msg, retryListener));
    }

    @MainThread
    public void onError(String opName, Throwable throwable, RetryListener retryListener) {
        stateLiveData.setValue(new ErrorData(opName, throwable, retryListener));

    }

    @MainThread
    public void onLoading(String opName) {
        stateLiveData.setValue(new LoadingData(opName));
    }


    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
    }

    public void observeState(@NonNull LifecycleOwner owner, @NonNull Observer<? super StateData> observer) {
        stateLiveData.observe(owner, observer);
        if (observer instanceof StateDataObserver) {
            ((StateDataObserver) observer).setLifeCycleOwner(owner);
        }
    }

    @Nullable
    @Override
    public T getValue() {
        return super.getValue();
    }
}
