package com.ui.lzs.viewAdapter;

import android.view.View;
import android.view.ViewGroup;

import com.ui.lzs.viewEvent.ObserverBase;
import com.ui.lzs.viewEvent.TabObserverSub;


/**
 * Created by FuQi on 2016/1/5.
 * ������
 * ����ע��
 * �۲���ģʽ�������ݱ仯
 */
public abstract  class TabGroupAdapter<T> {

    public T[] mt;
    public TabObserverSub mTabObserverSub=new TabObserverSub();
    //��ʼ�����캯����
    public TabGroupAdapter(T[] mt) {
        this.mt = mt;

    }

    //�õ�view������
    public int getCount(){

        return mt==null?-1:mt.length;

    }
    //�õ�ÿһ�������
    public T getItem(int position){

        return mt[position];
    }
    /**
     *  �õ�ÿһ���view
     *  �ó���Ϊ�˿�����չ
     *  ʹ��ViewGroup��������������view��Ҳ������viewgroup
     */

    public abstract View getview(ViewGroup viewGroup,int position, T t);

    //��Ӽ�����
    public  void registerObserver(ObserverBase mTabObserver){
        mTabObserverSub.register(mTabObserver);
    }
    //ȡ��������
    public  void  unregisterObserver(ObserverBase mTabObserver){
        mTabObserverSub.unregister(mTabObserver);
    }
    //֪ͨ���ݸı�
   public void notifaceTabChanged(){
       mTabObserverSub.notifaceChanged();
   }
}
