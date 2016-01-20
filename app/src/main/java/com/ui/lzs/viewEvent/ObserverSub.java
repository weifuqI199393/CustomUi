package com.ui.lzs.viewEvent;

import java.util.ArrayList;

/**
 * Created by FuQi on 2016/1/14.
 * ����۲���������
 * ���ܣ�ע��۲��ߣ�ȡ���۲���
 */
public abstract class ObserverSub<T> {

    //��Ź۲���
    public final ArrayList<T> dateObserveList=new ArrayList<T>();

    //ע��۲���
    public void register(T Observe){
        synchronized(dateObserveList) {
            if (null != dateObserveList && dateObserveList.size() >= 0) dateObserveList.add(Observe);
        }
    }
    //ȡ���۲���
    public void unregister(T Observe){
        synchronized(dateObserveList) {
            if (null != dateObserveList && dateObserveList.size() > 0)
                dateObserveList.remove(Observe);
        }
    }
    //ȡ�����й۲���
    public void unregisterAll(T Observe){
        synchronized(dateObserveList) {
        if (null!=dateObserveList&&dateObserveList.size()>0) dateObserveList.removeAll(dateObserveList);

            }
    }



}
