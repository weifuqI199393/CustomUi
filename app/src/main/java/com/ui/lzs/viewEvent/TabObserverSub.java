package com.ui.lzs.viewEvent;

/**
 * Created by FuQi on 2016/1/14.
 */
public class TabObserverSub extends ObserverSub<ObserverBase> {


     public void notifaceChanged () {

         if (dateObserveList.size() > 0) {

             for (int i = 0; i < dateObserveList.size(); i++) {

                dateObserveList.get(i).onchange();
             }

         }

     }


}
