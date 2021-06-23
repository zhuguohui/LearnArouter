package com.example.trscommand.load;


import com.example.trscommand.load.api.IStateView;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:43
 * Desc:只是用来标记正在加载状态
 */
public final class LoadingData extends StateData {
    float progress= IStateView.NO_PROGRESS;

    public LoadingData(String opName) {
        super(opName);
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float getProgress() {
        return progress;
    }
}
