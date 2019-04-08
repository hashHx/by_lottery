package com.hash.by_lottery.utils;

import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.entities.LongDragon;


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


    public static List countRankAndState(int type, ExLotteryTicket ticket, ExLotteryTicket preTicket) {

        int[] code = lotteryCodeAdapter.toCalculate(ticket.getDraw_code());
        int[] code_pre = lotteryCodeAdapter.toCalculate(preTicket.getDraw_code());

        ArrayList DT = (ArrayList) lotteryUtils.DragonTiger(lotteryCodeAdapter.toCalculate(ticket.getDraw_code()));
        ArrayList DT_pre = (ArrayList) lotteryUtils.DragonTiger(lotteryCodeAdapter.toCalculate(preTicket.getDraw_code()));


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
                    countState_1.add(true);// 11 改为 6
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
                if (lotteryUtils.sum_BS(code, 2) % 2 == 0 && lotteryUtils.sum_BS(code_pre, 2) % 2 == 0) {
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
                    if ((code[i] >= 11 && code_pre[i] >=11)) {
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
                    if ((code[i] < 11 && code_pre[i] < 11)) {
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

                if ((Integer) DT.get(0) == 0 && (Integer) DT_pre.get(0) == 0) {
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
                if ((Integer)DT.get(0) == 1 && (Integer)DT_pre.get(0) == 1) {
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

    /**
     * @Description: 创建longDragon基本列表
     * @Param: [list]
     * @return: java.util.List
     * @Author: Hash
     * @Date: 2019/4/2
     */
    public static List positionMarker(List<List> list) {
        List arr = new ArrayList();
        List array = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arr = (List) list.get(i);
            for (int j = 0; j < arr.size(); j++) {
                if ((boolean) arr.get(j)) {
                    array.add(new LongDragon().setRank(j).setState(i).setCount(2));
                }
            }
        }
        return array;
    }

    /** 
    * @Description: 计算长龙COUNT数 
    * @Param: [PM, CRAS] 
    * @return: boolean
    * @Author: Hash 
    * @Date: 2019/4/2 
    */ 
    public static boolean counter(List<LongDragon> PM, List<List> CRAS) {
        boolean flag = false;
        for (int i = 0; i < PM.size(); i++) {
            int count = PM.get(i).getCount();
            int rank = PM.get(i).getRank();
            int state = PM.get(i).getState();
            if ((boolean)CRAS.get(state).get(rank)){
                PM.get(i).setCount(count+1);
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }

}





