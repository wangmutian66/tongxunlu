package com.example.wangmutian.slidermenus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangmutian on 2018/2/6.
 */

public class QuickIndexBar extends View {
    private String[] indexArr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private Paint paint;
    private int measureWidth;
    private float cellheight;
    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 用来初始化View 宽度的
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measureWidth = getMeasuredWidth();
        cellheight = getMeasuredHeight()*1f/indexArr.length;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<indexArr.length;i++){
            Log.e("字母",indexArr[i]);
            paint.setColor((lastindex==i)?Color.BLACK:Color.WHITE);
            float x = measureWidth/2;
            float y =cellheight/2 + getTextHeight(indexArr[i])/2 + i*cellheight;
            //canvas.drawText 绘画的是文字的底部的坐标
            canvas.drawText(indexArr[i],x,y,paint);

        }
    }

    /**
     * 获取文本高度
     * @param text
     * @return
     */
    private int getTextHeight(String text) {
        //获取文本高度
        Rect bounds=new Rect(); //矩形对象
        paint.getTextBounds(text,0, text.length(),bounds);
        return bounds.height(); //获取文本高度
    }
    private int lastindex = -1; //记录上次的字母触摸的索引
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y2=event.getY();
                int index= (int) (y2/cellheight);
                if(lastindex!=index){


                    if(index>=0 && index<indexArr.length) {

                        if (listener != null) {
                            //Log.e("listener字母",indexArr[index]);
                            listener.onTouchLetter(indexArr[index]);
                        }
                    }

                }
                lastindex=index;
                break;
            case MotionEvent.ACTION_UP:
                //抬起后要重置一下
                listener.onTouchEnd();
                lastindex = -1;
                break;
        }

        //引起重绘 回调 onDraw
        invalidate();

        return true;
    }


    public OnTouchLetterListener listener;

    public void setOnTouchLetterListener(OnTouchLetterListener listener){
        this.listener=listener;
    }

    /**
     * 触摸字母的监听
     */
    public interface OnTouchLetterListener{
        void onTouchLetter(String letter);
        void onTouchEnd();
    }

}
