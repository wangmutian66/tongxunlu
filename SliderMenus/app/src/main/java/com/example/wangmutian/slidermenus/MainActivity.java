package com.example.wangmutian.slidermenus;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangmutian.slidermenus.bean.Friends;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quickIndexBar)
    QuickIndexBar quickIndexBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.curront)
    TextView curront;
    private ArrayList<Friends> friends = new ArrayList<Friends>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener() {

            @Override
            public void onTouchLetter(String letter) {
                Log.e("外界", letter);
                showCurrentWord(letter);

                //根据当前触摸的字母，去集合中去找哪个item的首字母和letter 一样然后将对于的item 放到屏幕顶端
                for (int i = 0; i < friends.size(); i++) {
                    String firstWord = friends.get(i).getPinyin().charAt(0) + "";
                    if (letter.equals(firstWord)) {
                        //说明要将当前的item 放到屏幕顶端
                        listview.setSelection(i);
                        break;
                    }
                }

            }



            @Override
            public void onTouchEnd() {
//                curront.setVisibility(View.GONE);
            }
        });

        //1.准备数据
        fillList();
        //2.对数据排序
        Collections.sort(friends);

        //3.设置adapter
        listview.setAdapter(new MyAdapter(friends, this));


    }

    private void showCurrentWord(String letter) {
        curront.setText(letter);
        curront.setVisibility(View.VISIBLE);
        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);
        //延时 隐藏 run 在主线程执行
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                curront.setVisibility(View.GONE);
            }
        },5000);
    }

    private Handler handler = new Handler();


    private void fillList() {
        // 虚拟数据
        friends.add(new Friends("李伟"));
        friends.add(new Friends("张三"));
        friends.add(new Friends("阿三"));
        friends.add(new Friends("阿四"));
        friends.add(new Friends("段誉"));
        friends.add(new Friends("段正淳"));
        friends.add(new Friends("张三丰"));
        friends.add(new Friends("陈坤"));
        friends.add(new Friends("林俊杰1"));
        friends.add(new Friends("陈坤2"));
        friends.add(new Friends("王二a"));
        friends.add(new Friends("林俊杰a"));
        friends.add(new Friends("张四"));
        friends.add(new Friends("林俊杰"));
        friends.add(new Friends("王二"));
        friends.add(new Friends("王二b"));
        friends.add(new Friends("赵四"));
        friends.add(new Friends("杨坤"));
        friends.add(new Friends("赵子龙"));
        friends.add(new Friends("杨坤1"));
        friends.add(new Friends("李伟1"));
        friends.add(new Friends("宋江"));
        friends.add(new Friends("宋江1"));
        friends.add(new Friends("李伟3"));
    }

}
