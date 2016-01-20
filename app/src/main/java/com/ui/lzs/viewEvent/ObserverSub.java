package com.ui.lzs.viewEvent;

import java.util.ArrayList;

/**
 * Created by FuQi on 2016/1/14.
 * 抽象观察者主题类
 * 功能：注册观察者，取消观察者
 */
public abstract class ObserverSub<T> {

    //存放观察者
    public final ArrayList<T> dateObserveList=new ArrayList<T>();

    //注册观察者
    public void register(T Observe){
        synchronized(dateObserveList) {
            if (null != dateObserveList && dateObserveList.size() >= 0) dateObserveList.add(Observe);
        }
    }
    //取消观察者
    public void unregister(T Observe){
        synchronized(dateObserveList) {
            if (null != dateObserveList && dateObserveList.size() > 0)
                dateObserveList.remove(Observe);
        }
    }
    //取消所有观察者
    public void unregisterAll(T Observe){
        synchronized(dateObserveList) {
        if (null!=dateObserveList&&dateObserveList.size()>0) dateObserveList.removeAll(dateObserveList);

            }
    }



}
