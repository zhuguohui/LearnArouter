package com.example.trscommand.load.api;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:03
 * Desc:将数据加载可能出现的结果对应的UI状态，抽象成相应的接口。
 * 方便以后的扩张
 */
public interface IStateView<T> {

    float NO_PROGRESS=-1.0f;



    void onError(String msg, RetryListener retryListener);

    /**
     * 当成功的时候，需要显示的UI
     * @param opName  操作的名称 方便UI提示用户
     * @param t 获取到的数据
     */
    void onSuccess(String opName, T t);

    /**
     * 正在加载的UI
     * @param opName 操作说明
     * @param  progress 进度信息，正常的范围在[0,1]
     *                  没有进度信息，传{@link #NO_PROGRESS}
     */
    void onLoading(String opName, float progress);


    /**
     *加载数据为空的UI
     */
    void onEmpty();

    /**
     * 加载网络错误的UI
     */
    void onNetError(RetryListener retryListener);

    /**
     * 不再需要UI的时候，会调用该方法
     * 需要对一些资源进行回收。或者对动画进行取消
     * 避免内存泄漏。
     */
    void onRelease();
}
