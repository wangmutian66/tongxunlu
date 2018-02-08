package com.example.wangmutian.slidermenus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wangmutian.slidermenus.Util.PinYinUtil;
import com.example.wangmutian.slidermenus.bean.Friends;

import java.util.ArrayList;

/**
 * Created by wangmutian on 2018/2/8.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Friends> friendses=new ArrayList<Friends>();
    private Context context;

    public MyAdapter(ArrayList<Friends> friendses, Context context) {
        this.friendses = friendses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friendses.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder = null;
//        if(view != null){
//            view = View.inflate(context,R.layout.adapter_friend,null);
//            viewHolder=new ViewHolder();
//            viewHolder.name=view.findViewById(R.id.name);
//            viewHolder.first_word=view.findViewById(R.id.first_word);
//            view.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        return view;
//    }
//
//    static class ViewHolder{
//        TextView name,first_word;
//
//    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(context,R.layout.adapter_friend,null);
        }
        ViewHolder holder = ViewHolder.getHolder(view);
        //设置数据
        Friends friends = friendses.get(i);
        holder.name.setText(friends.getName());

        String currentWord = PinYinUtil.getPinYin(friends.getName()).charAt(0)+"";
        if(i>0){
            String lastword = friendses.get(i - 1).getPinyin().charAt(0)+"";
            //当前首字母和上一个首字母比较
            if(currentWord.equals(lastword)){
                holder.first_word.setVisibility(View.GONE);
            }else{
                //
                holder.first_word.setVisibility(View.VISIBLE);
                holder.first_word.setText(currentWord);
            }
        }else{
            holder.first_word.setVisibility(View.VISIBLE);
            holder.first_word.setText(currentWord);
        }


        return view;
    }

    static class ViewHolder{
        TextView name,first_word;
        public ViewHolder(View view){
            name=view.findViewById(R.id.name);
            first_word=view.findViewById(R.id.first_word);
        }

        public static ViewHolder getHolder(View view){
            ViewHolder holder = (ViewHolder) view.getTag();
            if(holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }


    }


}
