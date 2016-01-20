package com.ui.lzs.test;

import android.app.Activity;
import android.os.Bundle;




public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CircleImageViewText cim= new CircleImageViewText(this,Color.BLACK,90);
//        MaterialProgressDrawable d=new MaterialProgressDrawable(this,cim);
//        d.setBackgroundColor(Color.WHITE);
//        d.setColorSchemeColors(Color.RED, Color.RED, Color.RED);
//        cim.setImageDrawable(d);
//        LinearLayout ll=new LinearLayout(this);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        ll.setGravity(Gravity.CENTER);
//        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(190, 190);
//        ll.setBackgroundColor(Color.BLACK);
//        ll.addView(cim,90,90);
//        addContentView(ll, llp);
//        d.start();

//        ListView list = (ListView)findViewById(R.id.list);
//
////定义数据源作为ListView内容
//        String [] arr_data = {"数据1","数据2","数据3","数据4"};
//
////新建一个数组适配器ArrayAdapter绑定数据，参数(当前的Activity，布局文件，数据源)
//        BaseAdapter arr_adapter=new Baseadapter();
//
////视图(ListView)加载适配器
//        list.setAdapter(arr_adapter);
//        final TimeDown vf=(TimeDown)findViewById(R.id.firstview);
//        vf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!vf.isAuto())
//                    vf.setIsAuto(true);
//
//            }
//        });
//        vf.setText("开始");//开始和结束显示的文字
//        vf.setTextSize(33);
//        vf.setTime(60);
//        vf.setTextColor(getResources().getColor(R.color.gray_txt));

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        TextView tv= (TextView) findViewById(R.id.tv1);
//        Log.v("cc", "w," +tv.getMeasuredWidth() + ",h," +tv.getMeasuredHeight());
//        ViewGroup.LayoutParams lp=tv.getLayoutParams();
//        Log.v("cc","w2,"+lp.width+",h2,"+lp.height);
//        int width= View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int height= View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        tv.measure(width,height);
//        Log.v("cc", "w3," + tv.getMeasuredWidth() + ",h3," + tv.getMeasuredHeight());
//    }
}
