package com.hash.by_lottery.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    static int[][] animal = new int[][]{new int[]{12, 24, 36, 48}, new int[]{11, 23, 35, 47}, new int[]{10, 22, 34, 46}, new int[]{9, 21, 33, 45}, new int[]{8, 20, 32, 44},
            new int[]{7, 19, 31, 43}, new int[]{6, 18, 30, 42}, new int[]{5, 17, 29, 41}, new int[]{4, 16, 28, 40}, new int[]{3, 15, 27, 39}, new int[]{2, 14, 26, 38}, new int[]{1, 13, 25, 37, 49}};

    static int[][] color = new int[][]{new int[]{1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40,45,46}, new int[]{5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49},
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
