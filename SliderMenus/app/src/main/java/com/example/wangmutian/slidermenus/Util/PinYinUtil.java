package com.example.wangmutian.slidermenus.Util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * [汉字转拼音]
 * Created by wangmutian on 2018/2/8.
 */

public class PinYinUtil {
    /**
     * 获取汉字的拼音,会销毁一定资源,所以不应该频繁调用
     * @param chinses
     * @return
     */
    public static String getPinYin(String chinses){
        if(TextUtils.isEmpty(chinses)) return null;

        //用来转化拼音的大小写
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE); //设置转换的拼音是大写字母
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);    //WITH_TONE_MARK 这是带声调的  WITH_TONE_NUMBER 拼音 的声调用数组显示 WITHOUT_TONE 不带声调的
        //

        //由于只能对单个汉字转换 所以将字符串转换成字符数组，然后对每个 字符转化，最后拼接起来
        char[] charArray = chinses.toCharArray();
        String pinyin = "";
        for(int i = 0; i<charArray.length;i++){
            //2.过滤空格
            if(Character.isWhitespace(charArray[i])) continue;
            //3.需要判断是否是汉字
            //汉字占2个字节 一个字节范围是 -128~127 那么汉字肯定大于127
            if(charArray[i]>127){
                //可能是汉字
                try {
                    //由于有多音字的存在
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);
                    if(pinyinArr != null){
                        pinyin += pinyinArr[0]; //即使有多音字 那么也只能取第一个拼音
                    }else{
                        //说明没有找到对应的汉字 汉字有问题 或者可能不是汉字，则忽略
                        pinyin += charArray[i]; // 0
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    //说明转化失败，不是汉字 比如 o(*￣︶￣*)o 那么则忽略
                    pinyin += charArray[i]; // 0
                }
            }else{
                //肯定不是汉字，应该是键盘上能够直接输入的字符
                pinyin += charArray[i];
            }

        }

        return pinyin;
    }



}
