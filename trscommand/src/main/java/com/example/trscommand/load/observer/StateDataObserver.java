package com.example.trscommand.load.observer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.trscommand.load.EmptyData;
import com.example.trscommand.load.ErrorData;
import com.example.trscommand.load.LoadingData;
import com.example.trscommand.load.NetErrorData;
import com.example.trscommand.load.StateData;
import com.example.trscommand.load.SuccessData;
import com.example.trscommand.load.api.IStateView;


/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:52
 * Desc:用于根据StateData的类型，自动调用IStateView的相关方法
 */
public class StateDataObserver<T> implements Observer<StateData>, LifecycleObserver {
    IStateView<T> iStateView;


    public StateDataObserver(IStateView<T> iStateView) {
        this.iStateView = iStateView;
    }

    public void setLifeCycleOwner(LifecycleOwner owner) {
        owner.getLifecycle().addObserver(this);
    }

    @Override
    public void onChanged(StateData stateData) {
        if (stateData instanceof LoadingData) {
            LoadingData loadingData = (LoadingData) stateData;
            iStateView.onLoading(loadingData.getOpName(), loadingData.getProgress());


        } else if (stateData instanceof ErrorData) {

            ErrorData errorData = (ErrorData) stateData;
            iStateView.onError(errorData.getMsg(), errorData.getRetryListener());

        } else if (stateData instanceof SuccessData) {

            SuccessData<T> successData = (SuccessData<T>) stateData;
            iStateView.onSuccess(successData.getOpName(), successData.getData());

        } else if (stateData instanceof EmptyData) {

            iStateView.onEmpty();

        } else if (stateData instanceof NetErrorData) {

            NetErrorData netErrorData = (NetErrorData) stateData;
            iStateView.onNetError(netErrorData.getRetryListener());

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        iStateView.onRelease();
    }
}
