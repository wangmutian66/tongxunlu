package com.example.wangmutian.slidermenus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quickIndexBar)
    QuickIndexBar quickIndexBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener(){

            @Override
            public void onTouchLetter(String letter) {
                Log.e("外界",letter);
            }
        });


    }


}
