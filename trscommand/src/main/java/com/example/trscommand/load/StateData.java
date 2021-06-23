package com.example.trscommand.load;

/**
 * Created by zhuguohui
 * Date: 2021/6/7
 * Time: 17:40
 * Desc:使用一个类来抽象具有状态类的父类
 */
public  class StateData {
    String opName;//操作的名称，比如签发，撤稿等。用于UI显示

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpName() {
        return opName;
    }

    public StateData(String opName) {
        this.opName = opName;
    }
}
