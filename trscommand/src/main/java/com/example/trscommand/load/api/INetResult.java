package com.example.trscommand.load.api;

import java.lang.reflect.Type;

/**
 * @author zhuguohui
 * @description:定义的一个接口，用来表示服务器返回的结果 因为服务器返回的数据样式各种各样。有List 有的是一个对象。如果失败返回的类型也不一样。
 * 所以需要定义一个接口来屏蔽这些变化。
 * @date :2021/6/3 10:04
 */
public interface INetResult<T> {

    /**
     * 从服务器返回的数据是否匹配当前类型
     *
     * @return
     */
    boolean isMatches(String jsonStr, Type targetType);

    /**
     * 获取数据
     *
     * @return
     */
    T getData();

    /**
     * 数据访问是否成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 获取提示信息
     *
     * @return
     */
    String getMessage();



}