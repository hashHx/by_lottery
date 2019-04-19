package com.hash.by_lottery.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Mark6Utils
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/12 14:49
 * @Version 1.0
 **/

public class Mark6Utils {

    static int[] years = new int[]{
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12
    };

    Map<String, String> year_month_day = new HashMap<String, String>();

    Mark6Utils() {

        year_month_day.put("1","2019-01-03 21:30:00");
        year_month_day.put("2","2019-01-05 21:30:00");
        year_month_day.put("3","2019-01-08 21:30:00");
        year_month_day.put("4","2019-01-10 21:30:00");
        year_month_day.put("5","2019-01-13 21:30:00");
        year_month_day.put("6","2019-01-15 21:30:00");
        year_month_day.put("7","2019-01-17 21:30:00");
        year_month_day.put("8","2019-01-19 21:30:00");
        year_month_day.put("9","2019-01-22 21:30:00");
        year_month_day.put("10","2019-01-24 21:30:00");
        year_month_day.put("11","2019-01-26 21:30:00");
        year_month_day.put("12","2019-01-29 21:30:00");
        year_month_day.put("13","2019-01-31 21:30:00");
        year_month_day.put("14","2019-02-03 21:30:00");
        year_month_day.put("15","2019-02-06 21:30:00");
        year_month_day.put("16","2019-02-09 21:30:00");
        year_month_day.put("17","2019-02-12 21:30:00");
        year_month_day.put("18","2019-02-14 21:30:00");
        year_month_day.put("19","2019-02-16 21:30:00");
        year_month_day.put("20","2019-02-19 21:30:00");
        year_month_day.put("21","2019-02-21 21:30:00");
        year_month_day.put("22","2019-02-23 21:30:00");
        year_month_day.put("23","2019-02-26 21:30:00");
        year_month_day.put("24","2019-02-28 21:30:00");
        year_month_day.put("25","2019-03-03 21:30:00");
        year_month_day.put("26","2019-03-05 21:30:00");
        year_month_day.put("27","2019-03-07 21:30:00");
        year_month_day.put("28","2019-03-09 21:30:00");
        year_month_day.put("29","2019-03-12 21:30:00");
        year_month_day.put("30","2019-03-14 21:30:00");
        year_month_day.put("31","2019-03-16 21:30:00");
        year_month_day.put("32","2019-03-19 21:30:00");
        year_month_day.put("33","2019-03-21 21:30:00");
        year_month_day.put("34","2019-03-23 21:30:00");
        year_month_day.put("35","2019-03-26 21:30:00");
        year_month_day.put("36","2019-03-28 21:30:00");
        year_month_day.put("37","2019-03-30 21:30:00");
        year_month_day.put("38","2019-04-02 21:30:00");
        year_month_day.put("39","2019-04-04 21:30:00");
        year_month_day.put("40","2019-04-06 21:30:00");
        year_month_day.put("41","2019-04-09 21:30:00");
        year_month_day.put("42","2019-04-11 21:30:00");
        year_month_day.put("43","2019-04-13 21:30:00");
        year_month_day.put("44", "2019-04-16 21:30:00");
        year_month_day.put("45", "2019-04-20 21:30:00");
        year_month_day.put("46", "2019-04-23 21:30:00");
        year_month_day.put("47", "2019-04-25 21:30:00");
        year_month_day.put("48", "2019-04-27 21:30:00");
        year_month_day.put("49", "2019-04-30 21:30:00");
        year_month_day.put("50", "2019-05-02 21:30:00");
        year_month_day.put("51", "2019-05-04 21:30:00");
        year_month_day.put("52", "2019-05-07 21:30:00");
        year_month_day.put("53", "2019-05-09 21:30:00");
        year_month_day.put("54", "2019-05-12 21:30:00");
        year_month_day.put("55", "2019-05-14 21:30:00");
        year_month_day.put("56", "2019-05-16 21:30:00");
        year_month_day.put("57", "2019-05-19 21:30:00");
        year_month_day.put("58", "2019-05-21 21:30:00");
        year_month_day.put("59", "2019-05-23 21:30:00");
        year_month_day.put("60", "2019-05-25 21:30:00");
        year_month_day.put("61", "2019-05-28 21:30:00");
        year_month_day.put("62", "2019-06-01 21:30:00");
        year_month_day.put("63", "2019-06-04 21:30:00");
        year_month_day.put("64", "2019-06-06 21:30:00");
        year_month_day.put("65", "2019-06-09 21:30:00");
        year_month_day.put("66", "2019-06-11 21:30:00");
        year_month_day.put("67", "2019-06-13 21:30:00");
        year_month_day.put("68", "2019-06-15 21:30:00");
        year_month_day.put("69", "2019-06-18 21:30:00");
        year_month_day.put("70", "2019-06-20 21:30:00");
        year_month_day.put("71", "2019-06-22 21:30:00");
        year_month_day.put("72", "2019-06-25 21:30:00");
        year_month_day.put("73", "2019-06-27 21:30:00");
        year_month_day.put("74", "2019-06-29 21:30:00");

    }

    static int[][] animal = new int[][]{new int[]{12, 24, 36, 48}, new int[]{11, 23, 35, 47}, new int[]{10, 22, 34, 46}, new int[]{9, 21, 33, 45}, new int[]{8, 20, 32, 44},
            new int[]{7, 19, 31, 43}, new int[]{6, 18, 30, 42}, new int[]{5, 17, 29, 41}, new int[]{4, 16, 28, 40}, new int[]{3, 15, 27, 39}, new int[]{2, 14, 26, 38}, new int[]{1, 13, 25, 37, 49}};

    static int[][] color = new int[][]{new int[]{1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46}, new int[]{5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49},
            new int[]{3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48}}; //red1 green2
//    public static int getYearAnimal() {
//        Integer start = 1900;
//        Integer year = Integer.parseInt(lotteryUtils.timeStamp2Date(String.valueOf(System.currentTimeMillis()), "YYYY"));
////        String [] years=new String[]{
////                "鼠","牛","虎","兔",
////                "龙","蛇","马","羊",
////                "猴","鸡","狗","猪"
////        };
//
//        return years[(year - start) % years.length];
    //   }

    public static int[] getAnimals(int[] code) {
        int[] result = new int[code.length];
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < 12; j++) {
                int size = animal[j].length;
                for (int k = 0; k < size; k++) {
                    if (code[i] == animal[j][k]) {
                        result[i] = j + 1;
                    }
                }
            }
        }
        return result;
    }

    public static int[] getColors(int[] code) {
        int[] result = new int[code.length];
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < 3; j++) {
                int size = color[j].length;
                for (int k = 0; k < size; k++) {
                    if (code[i] == color[j][k]) {
                        result[i] = j + 1;
                    }
                }
            }
        }
        return result;
    }


}
