package com.example.wangmutian.slidermenus.bean;

import android.support.annotation.NonNull;

import com.example.wangmutian.slidermenus.Util.PinYinUtil;

import java.util.Comparator;

/**
 * Created by wangmutian on 2018/2/8.
 */

public class Friends implements Comparable<Friends> {
    private String name;
    private String pinyin;



    //使用成员变量生成构造方法 alt + insert
    public Friends(String name) {
        this.name = name;
        setPinyin(PinYinUtil.getPinYin(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Friends friends) {
//        String pinyin = PinYinUtil.getPinYin(name);
//        String friendspinyin = PinYinUtil.getPinYin(friends.getName());
        return getPinyin().compareTo(friends.getPinyin());
    }


    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
