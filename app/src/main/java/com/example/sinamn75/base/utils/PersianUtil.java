package com.example.sinamn75.base.utils;

import java.text.NumberFormat;
import java.util.Locale;


public class PersianUtil {
    private static NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar", "EG"));

    public static String getNumberPersian(String textPost) {
        textPost = textPost.replace("1", "۱");
        textPost = textPost.replace("2", "۲");
        textPost = textPost.replace("3", "۳");
        textPost = textPost.replace("4", "۴");
        textPost = textPost.replace("5", "۵");
        textPost = textPost.replace("6", "۶");
        textPost = textPost.replace("7", "۷");
        textPost = textPost.replace("8", "۸");
        textPost = textPost.replace("9", "۹");
        textPost = textPost.replace("0", "۰");
        return textPost;
    }

    public static String getNumberPersian(long Post) {
        return getNumberPersian(String.valueOf(Post));
    }

    public static String getNumberEnglish(String textPost) {
        textPost = textPost.replace("۱", "1");
        textPost = textPost.replace("۲", "2");
        textPost = textPost.replace("۳", "3");
        textPost = textPost.replace("۴", "4");
        textPost = textPost.replace("۵", "5");
        textPost = textPost.replace("۶", "6");
        textPost = textPost.replace("۷", "7");
        textPost = textPost.replace("۸", "8");
        textPost = textPost.replace("۹", "9");
        textPost = textPost.replace("۰", "0");
        return textPost;
    }

    public static String getMonthOfYearPersian(String textPost) {
        textPost = textPost.replace("۰۱", "فروردین");
        textPost = textPost.replace("۰۲", "اردیبهشت");
        textPost = textPost.replace("۰۳", "خرداد");
        textPost = textPost.replace("۰۴", "تیر");
        textPost = textPost.replace("۰۵", "مرداد");
        textPost = textPost.replace("۰۶", "شهریور");
        textPost = textPost.replace("۰۷", "مهر");
        textPost = textPost.replace("۰۸", "آبان");
        textPost = textPost.replace("۰۹", "آذر");
        textPost = textPost.replace("۱۰", "دی");
        textPost = textPost.replace("۱۱", "بهمن");
        textPost = textPost.replace("۱۲", "اسفند");
        return textPost;
    }

    public static String getDayNameOfWeek(String textPost) {
        textPost = textPost.replace("Sunday", "یک شنبه");
        textPost = textPost.replace("Monday", "دو شنبه");
        textPost = textPost.replace("Tuesday", "سه شنبه");
        textPost = textPost.replace("Wednesday", "چهار شنبه");
        textPost = textPost.replace("Thursday", "پنج شنبه");
        textPost = textPost.replace("Friday", "جمعه");
        textPost = textPost.replace("Saturday", "شنبه");
        return textPost;
    }


    public static String getNumberEnglish(long Post) {
        return getNumberEnglish(String.valueOf(Post));
    }
}
