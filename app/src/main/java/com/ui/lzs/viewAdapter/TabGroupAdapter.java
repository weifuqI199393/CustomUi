package com.ui.lzs.viewAdapter;

import android.view.View;
import android.view.ViewGroup;

import com.ui.lzs.viewEvent.ObserverBase;
import com.ui.lzs.viewEvent.TabObserverSub;


/**
 * Created by FuQi on 2016/1/5.
 * 适配器
 * 代码注释
 * 观察者模式监听数据变化
 */
public abstract  class TabGroupAdapter<T> {

    public T[] mt;
    public TabObserverSub mTabObserverSub=new TabObserverSub();
    //初始化构造函数，
    public TabGroupAdapter(T[] mt) {
        this.mt = mt;

    }

    //得到view的数量
    public int getCount(){

        return mt==null?-1:mt.length;

    }
    //得到每一项的数据
    public T getItem(int position){

        return mt[position];
    }
    /**
     *  得到每一项的view
     *  用抽象为了可以扩展
     *  使用ViewGroup，更加灵活，可以是view，也可以是viewgroup
     */

    public abstract View getview(ViewGroup viewGroup,int position, T t);

    //添加监听者
    public  void registerObserver(ObserverBase mTabObserver){
        mTabObserverSub.register(mTabObserver);
    }
    //取消监听者
    public  void  unregisterObserver(ObserverBase mTabObserver){
        mTabObserverSub.unregister(mTabObserver);
    }
    //通知数据改变
   public void notifaceTabChanged(){
       mTabObserverSub.notifaceChanged();
   }
}
