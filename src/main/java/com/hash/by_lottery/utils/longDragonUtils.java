package com.hash.by_lottery.utils;

import com.alibaba.fastjson.JSONArray;

import com.hash.by_lottery.entities.ExLotteryTicket;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName longDragonUtils
 * @Descritption 长龙玩法工具类
 * @Author Hash
 * @Date 2019/3/27 20:37
 * @Version 1.0
 **/

/***
 * 单双大小龙虎 尾大小 合数单双
 */

public class longDragonUtils {



//    private volatile static longDragonUtils instance = null;
//
//
//    private longDragonUtils() {
//
//    }
//
//    public static longDragonUtils getInstance() {
//
//        if (instance == null) {
//            synchronized (longDragonUtils.class) {
//                if (instance == null) {
//                    instance = new longDragonUtils();
//                }
//            }
//        }
//        return instance;
//    }


    @Data
    @Accessors(chain = true)
    public class data {
        private int rank;
        private int state;
        private int count;
    }




    public static List countRankAndState(int type, ExLotteryTicket ticket, ExLotteryTicket preTicket) {
        int[] code = lotteryCodeAdapter.toCalculate(ticket.getDraw_code());
        int[] code_pre = lotteryCodeAdapter.toCalculate(preTicket.getDraw_code());

        ArrayList DT = (ArrayList) lotteryUtils.DragonTiger(lotteryCodeAdapter.toCalculate(ticket.getDraw_code()));
        ArrayList DT_pre = (ArrayList) lotteryUtils.DragonTiger(lotteryCodeAdapter.toCalculate(preTicket.getDraw_code()));

        System.out.println(DT);
        System.out.println(DT_pre);

        List<List<Boolean>> countStates = new ArrayList<>();

        List<Boolean> countState_1 = new ArrayList<>(); //单
        List<Boolean> countState_2 = new ArrayList<>(); //双
        List<Boolean> countState_3 = new ArrayList<>(); //大
        List<Boolean> countState_4 = new ArrayList<>(); //小
        List<Boolean> countState_5 = new ArrayList<>(); //龙
        List<Boolean> countState_6 = new ArrayList<>(); //虎
        List<Boolean> countState_7 = new ArrayList<>(); //
        List<Boolean> countState_8 = new ArrayList<>(); //
        List<Boolean> countState_9 = new ArrayList<>(); //
        List<Boolean> countState_10 = new ArrayList<>(); //

        switch (type) {
            case 1:
                //单
                for (int i = 0; i < 10; i++) {
                    if ((code[i] % 2 != 0 && code_pre[i] % 2 != 0)) {
                        countState_1.add(true);
                    } else {
                        countState_1.add(false);
                    }
                }
                if (lotteryUtils.sum_FS(code) % 2 != 0 && lotteryUtils.sum_FS(code_pre) % 2 != 0) {
                    countState_1.add(true);
                } else {
                    countState_1.add(false);
                }
                //双
                for (int i = 0; i < 10; i++) {
                    if ((code[i] % 2 == 0 && code_pre[i] % 2 == 0)) {
                        countState_2.add(true);
                    } else {
                        countState_2.add(false);
                    }
                }
                if (lotteryUtils.sum_FS(code) % 2 == 0 && lotteryUtils.sum_FS(code_pre) % 2 == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                //大
                for (int i = 0; i < 10; i++) {
                    if ((code[i] > 5 && code_pre[i] > 5)) {
                        countState_3.add(true);
                    } else {
                        countState_3.add(false);
                    }
                }
                if (lotteryUtils.sum_FS(code) > 11 && lotteryUtils.sum_FS(code_pre) > 11) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }

                //小
                for (int i = 0; i < 10; i++) {
                    if ((code[i] < 6 && code_pre[i] < 6)) {
                        countState_4.add(true);
                    } else {
                        countState_4.add(false);
                    }
                }
                if (lotteryUtils.sum_FS(code) <= 11 && lotteryUtils.sum_FS(code_pre) <= 11) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                //龙
                for (int i = 0; i < DT.size(); i++) {
                    System.out.println(DT.get(i)+"___"+DT_pre.get(i));
                    if ((Integer) DT.get(i) == 0 && (Integer) DT_pre.get(i) == 0) {
                        countState_5.add(true);
                    } else {
                        countState_5.add(false);
                    }
                }
                //虎
                for (int i = 0; i < DT.size(); i++) {
                    if ((Integer) DT.get(i) == 1 && (Integer) DT_pre.get(i) == 1) {
                        countState_6.add(true);
                    } else {
                        countState_6.add(false);
                    }
                }
                countStates.add(countState_1);
                countStates.add(countState_2);
                countStates.add(countState_3);
                countStates.add(countState_4);
                countStates.add(countState_5);
                countStates.add(countState_6);
                break;

            case 2:
                //单
                for (int i = 0; i < 5; i++) {
                    if ((code[i] % 2 != 0 && code_pre[i] % 2 != 0)) {
                        countState_1.add(true);
                    } else {
                        countState_1.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 2) % 2 != 0 && lotteryUtils.sum_BS(code_pre, 2) % 2 != 0) {
                    countState_1.add(10, true);
                } else {
                    countState_1.add(10, false);
                }
                //双
                for (int i = 0; i < 5; i++) {
                    if ((code[i] % 2 == 0 && code_pre[i] % 2 == 0)) {
                        countState_2.add(true);
                    } else {
                        countState_2.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 2) % 2 == 0 && lotteryUtils.sum_BS(code_pre, 2) % 2 == 0) {
                    countState_2.add(10, true);
                } else {
                    countState_2.add(10, false);
                }
                //大
                for (int i = 0; i < 5; i++) {
                    if ((code[i] > 5 && code_pre[i] > 5)) {
                        countState_3.add(true);
                    } else {
                        countState_3.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 2) > 22 && lotteryUtils.sum_BS(code_pre, 2) > 22) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                //小
                for (int i = 0; i < 5; i++) {
                    if ((code[i] < 6 && code_pre[i] < 6)) {
                        countState_4.add(true);
                    } else {
                        countState_4.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 2) <= 22 && lotteryUtils.sum_BS(code_pre, 2) <= 22) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                //龙

                if ((Integer) DT.get(0) == 0 && (Integer) DT_pre.get(0) == 0) {
                    countState_5.add(true);
                } else {
                    countState_5.add(false);
                }
                //虎
                if ((Integer) DT.get(0) == 1 && (Integer) DT_pre.get(0) == 1) {
                    countState_6.add(true);
                } else {
                    countState_6.add(false);
                }
                //和
                if ((Integer) DT.get(0) == 2 && (Integer) DT_pre.get(0) == 2) {
                    countState_7.add(true);
                } else {
                    countState_7.add(false);
                }
                countStates.add(countState_1);
                countStates.add(countState_2);
                countStates.add(countState_3);
                countStates.add(countState_4);
                countStates.add(countState_5);
                countStates.add(countState_6);
                countStates.add(countState_7);
                break;
            case 3:
                //单
                for (int i = 0; i < 5; i++) {
                    if ((code[i] % 2 != 0 && code_pre[i] % 2 != 0)) {
                        countState_1.add(true);
                    } else {
                        countState_1.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 3) % 2 != 0 && lotteryUtils.sum_BS(code_pre, 3) % 2 != 0) {
                    countState_1.add(true);
                } else {
                    countState_1.add(false);
                }
                if (lotteryUtils.tail_SD(code) == 0 && lotteryUtils.tail_SD(code_pre) == 0) {
                    countState_1.add(true);
                } else {
                    countState_1.add(false);
                }
                //双
                for (int i = 0; i < 5; i++) {
                    if ((code[i] % 2 == 0 && code_pre[i] % 2 == 0)) {
                        countState_2.add(true);
                    } else {
                        countState_2.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 3) % 2 == 0 && lotteryUtils.sum_BS(code_pre, 3) % 2 == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                if (lotteryUtils.tail_SD(code) == 0 && lotteryUtils.tail_SD(code_pre) == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                //大
                for (int i = 0; i < 5; i++) {
                    if ((code[i] > 5 && code_pre[i] > 5)) {
                        countState_3.add(true);
                    } else {
                        countState_3.add(false);
                    }
                }
                if (lotteryUtils.sum_FS(code) > 29 && lotteryUtils.sum_FS(code_pre) > 29) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 0 && lotteryUtils.tail_BS(code_pre) == 0) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                //小
                for (int i = 0; i < 5; i++) {
                    if ((code[i] < 6 && code_pre[i] < 6)) {
                        countState_4.add(true);
                    } else {
                        countState_4.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 3) <= 29 && lotteryUtils.sum_BS(code_pre, 3) <= 29) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 1 && lotteryUtils.tail_BS(code_pre) == 1) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                //龙

                if ((Integer) DT.get(0) == 0 && (Integer) DT_pre.get(0) == 0) {
                    countState_5.add(true);
                } else {
                    countState_5.add(false);
                }
                //虎
                if ((Integer) DT.get(0) == 1 && (Integer) DT_pre.get(0) == 1) {
                    countState_6.add(true);
                } else {
                    countState_6.add(false);
                }
                countStates.add(countState_1);
                countStates.add(countState_2);
                countStates.add(countState_3);
                countStates.add(countState_4);
                countStates.add(countState_5);
                countStates.add(countState_6);
                break;
            case 4:
                //单
                for (int i = 0; i < 8; i++) {
                    if ((code[i] % 2 != 0 && code_pre[i] % 2 != 0)) {
                        countState_1.add(true);
                    } else {
                        countState_1.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) % 2 != 0 && lotteryUtils.sum_BS(code_pre, 4) % 2 != 0) {
                    countState_1.add(true);
                } else {
                    countState_1.add(false);
                }
                //双
                for (int i = 0; i < 8; i++) {
                    if ((code[i] % 2 == 0 && code_pre[i] % 2 == 0)) {
                        countState_2.add(true);
                    } else {
                        countState_2.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) % 2 == 0 && lotteryUtils.sum_BS(code_pre, 4) % 2 == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                if (lotteryUtils.tail_SD(code) == 0 && lotteryUtils.tail_SD(code_pre) == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                //大
                for (int i = 0; i < 8; i++) {
                    if ((code[i] > 5 && code_pre[i] > 5)) {
                        countState_3.add(true);
                    } else {
                        countState_3.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) > 84 && lotteryUtils.sum_BS(code_pre, 4) > 84) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 0 && lotteryUtils.tail_BS(code_pre) == 0) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                //小
                for (int i = 0; i < 8; i++) {
                    if ((code[i] < 6 && code_pre[i] < 6)) {
                        countState_4.add(true);
                    } else {
                        countState_4.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) < 84 && lotteryUtils.sum_BS(code_pre, 4) < 84) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 1 && lotteryUtils.tail_BS(code_pre) == 1) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                //龙

                for (int i = 0; i < DT.size(); i++) {
                    System.out.println(DT.get(i)+"___"+DT_pre.get(i));
                    if ((Integer) DT.get(i) == 0 && (Integer) DT_pre.get(i) == 0) {
                        countState_5.add(true);
                    } else {
                        countState_5.add(false);
                    }
                }
                //rank 9 和
                if (lotteryUtils.sum_BS(code, 4) == 84 && lotteryUtils.sum_BS(code_pre, 4) == 84) {
                    countState_5.add(true);
                } else {
                    countState_5.add(false);
                }
                //虎
                for (int i = 0; i < DT.size(); i++) {
                    if ((Integer) DT.get(i) == 1 && (Integer) DT_pre.get(i) == 1) {
                        countState_6.add(true);
                    } else {
                        countState_6.add(false);
                    }
                }

                //尾大
                if (lotteryUtils.tail_BS(code) == 0 && lotteryUtils.tail_BS(code_pre) == 0) {
                    countState_7.add(true);
                } else {
                    countState_7.add(false);
                }

                //尾小
                if (lotteryUtils.tail_BS(code) == 1 && lotteryUtils.tail_BS(code_pre) == 1) {
                    countState_8.add(true);
                } else {
                    countState_8.add(false);
                }

                //合数单
                for (int i = 0; i < 8; i++) {
                    if (lotteryUtils.sum_number(code)[i] % 2 == 1 && lotteryUtils.sum_number(code_pre)[i] % 2 == 1) {
                        countState_9.add(true);
                    } else {
                        countState_9.add(false);
                    }
                }

                //合数双
                for (int i = 0; i < 8; i++) {
                    if (lotteryUtils.sum_number(code)[i] % 2 == 0 && lotteryUtils.sum_number(code_pre)[i] % 2 == 0) {
                        countState_10.add(true);
                    } else {
                        countState_10.add(false);
                    }
                }
                countStates.add(countState_1);
                countStates.add(countState_2);
                countStates.add(countState_3);
                countStates.add(countState_4);
                countStates.add(countState_5);
                countStates.add(countState_6);
                countStates.add(countState_7);
                countStates.add(countState_8);
                countStates.add(countState_9);
                countStates.add(countState_10);
                break;
            case 6:
                //单
                for (int i = 0; i < 8; i++) {
                    if ((code[i] % 2 != 0 && code_pre[i] % 2 != 0)) {
                        countState_1.add(true);
                    } else {
                        countState_1.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) % 2 != 0 && lotteryUtils.sum_BS(code_pre, 4) % 2 != 0) {
                    countState_1.add(true);
                } else {
                    countState_1.add(false);
                }
                //双
                for (int i = 0; i < 8; i++) {
                    if ((code[i] % 2 == 0 && code_pre[i] % 2 == 0)) {
                        countState_2.add(true);
                    } else {
                        countState_2.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) % 2 == 0 && lotteryUtils.sum_BS(code_pre, 4) % 2 == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                if (lotteryUtils.tail_SD(code) == 0 && lotteryUtils.tail_SD(code_pre) == 0) {
                    countState_2.add(true);
                } else {
                    countState_2.add(false);
                }
                //大
                for (int i = 0; i < 8; i++) {
                    if ((code[i] > 5 && code_pre[i] > 5)) {
                        countState_3.add(true);
                    } else {
                        countState_3.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) > 84 && lotteryUtils.sum_BS(code_pre, 4) > 84) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 0 && lotteryUtils.tail_BS(code_pre) == 0) {
                    countState_3.add(true);
                } else {
                    countState_3.add(false);
                }
                //小
                for (int i = 0; i < 8; i++) {
                    if ((code[i] < 6 && code_pre[i] < 6)) {
                        countState_4.add(true);
                    } else {
                        countState_4.add(false);
                    }
                }
                if (lotteryUtils.sum_BS(code, 4) < 84 && lotteryUtils.sum_BS(code_pre, 4) < 84) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                if (lotteryUtils.tail_BS(code) == 1 && lotteryUtils.tail_BS(code_pre) == 1) {
                    countState_4.add(true);
                } else {
                    countState_4.add(false);
                }
                //龙

                if (DT.get(0) == "0" && DT_pre.get(0) == "0") {
                    countState_5.add(true);
                } else {
                    countState_5.add(false);
                }
                if (lotteryUtils.sum_BS(code, 4) == 84 && lotteryUtils.sum_BS(code_pre, 4) == 84) {
                    countState_5.add(true);
                } else {
                    countState_5.add(false);
                }
                //虎
                if (DT.get(0) == "1" && DT_pre.get(0) == "1") {
                    countState_6.add(true);
                } else {
                    countState_6.add(false);
                }

                //尾大
                if (lotteryUtils.tail_BS(code) == 0 && lotteryUtils.tail_BS(code_pre) == 0) {
                    countState_7.add(true);
                } else {
                    countState_7.add(false);
                }

                //尾小
                if (lotteryUtils.tail_BS(code) == 1 && lotteryUtils.tail_BS(code_pre) == 1) {
                    countState_8.add(true);
                } else {
                    countState_8.add(false);
                }

                //合数单
                for (int i = 0; i < 8; i++) {
                    if (lotteryUtils.sum_number(code)[i] % 2 == 1 && lotteryUtils.sum_number(code_pre)[i] % 2 == 1) {
                        countState_9.add(true);
                    } else {
                        countState_9.add(false);
                    }
                }

                //合数双
                for (int i = 0; i < 8; i++) {
                    if (lotteryUtils.sum_number(code)[i] % 2 == 0 && lotteryUtils.sum_number(code_pre)[i] % 2 == 0) {
                        countState_10.add(true);
                    } else {
                        countState_10.add(false);
                    }
                }
                countStates.add(countState_1);
                countStates.add(countState_2);
                countStates.add(countState_3);
                countStates.add(countState_4);
                countStates.add(countState_5);
                countStates.add(countState_6);
                countStates.add(countState_7);
                countStates.add(countState_8);
                countStates.add(countState_9);
                countStates.add(countState_10);
                break;

        }
        return countStates;
    }

}

