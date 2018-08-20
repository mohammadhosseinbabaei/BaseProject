package com.example.sinamn75.base.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class PersianUtil {
    private static NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar", "EG"));

    private static String replacepersianNumber(String textPost) {
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

    private static String replaceenglishNumber(String textPost) {
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

    private static String getDayNameP(String textPost) {
        textPost = textPost.replace("Sunday", "یک شنبه");
        textPost = textPost.replace("Monday", "دو شنبه");
        textPost = textPost.replace("Tuesday", "سه شنبه");
        textPost = textPost.replace("Wednesday", "چهار شنبه");
        textPost = textPost.replace("Thursday", "پنج شنبه");
        textPost = textPost.replace("Friday", "جمعه");
        textPost = textPost.replace("Saturday", "شنبه");
        return textPost;
    }

    private static String getDayNameE(String textPost) {
        textPost = textPost.replace("یک شنبه", "Sunday");
        textPost = textPost.replace("دو‌شنبه", "Monday");
        textPost = textPost.replace("سه‌شنبه", "Tuesday");
        textPost = textPost.replace("چهار‌شنبه", "Tuesday");
        textPost = textPost.replace("پنج‌شنبه", "Thursday");
        textPost = textPost.replace("جمعه", "Friday");
        textPost = textPost.replace("جمعه", "Friday");
        textPost = textPost.replace("شنبه", "Saturday");
        return textPost;
    }

    private static String getMonthName(String textPost) {
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

    /**
     * Gets a number in english and replace it with persian Number.
     *
     * @param number Number in english.
     * @return Number in persian.
     */
    public static String getNumberPersian(String number) {
        return replacepersianNumber(number);
    }

    /**
     * Gets a number in persian and  replace it with english Number.
     *
     * @param number Number in persian.
     * @return Number in english.
     */
    public static String getNumberEnglish(String number) {
        return replaceenglishNumber(String.valueOf(number));
    }

    /**
     * Gets a day name in english and replace it with persian day name.
     *
     * @param day day name in english.
     * @return day name in persian.
     */
    public static String getDayNamePersian(String day) {
        return getDayNameP(String.valueOf(day));
    }

    /**
     * Gets a day name in persian and replace it with english day name.
     *
     * @param day day name in persian.
     * @return day name in english.
     */
    public static String getDayNameEnglish(String day) {
        return getDayNameE(String.valueOf(day));
    }

    /**
     * Gets a month name in english and replace it with persian month name.
     *
     * @param month month name in english.
     * @return month name in persian.
     */
    public static String getMonthNamePersian(String month) {
        return getMonthName(String.valueOf(month));
    }
}