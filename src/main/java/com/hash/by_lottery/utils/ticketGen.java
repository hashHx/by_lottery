package com.hash.by_lottery.utils;

import com.hash.by_lottery.entities.ExLotteryTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ticketGen
 * @Descritption 彩票data生成器
 * @Author Hash
 * @Date 2019/3/22 13:21
 * @Version 1.0
 **/

public class ticketGen {


    public static HashMap<Object , Object> getresult(ExLotteryTicket ticket, int type) {
        Map data = new HashMap();
        data.put("lotCode", ticket.getLot_code());
        data.put("lotName", ticket.getLot_name());
        data.put("serverTime", System.currentTimeMillis());
        Long time = Long.parseLong(lotteryUtils.date2TimeStamp(ticket.getDraw_time()));
        data.put("preDrawTime", time);
        data.put("nextDrawTime", String.valueOf(time + ticket.getLot_interval()));
        data.put("lotType", ticket.getLot_type());
        data.put("preDrawIssue", ticket.getDraw_issue());
        data.put("drawIssue", String.valueOf(Integer.valueOf(ticket.getDraw_issue()) + 1));
        data.put("iconUrl", ticket.getLot_imgUrl());
        data.put("videoImg", ticket.getLot_videoImg());
        data.put("videoUrl", ticket.getLot_videoUrl());
        data.put("totalCount", ticket.getLot_count());
        data.put("drawNumberArr", ticket.getDraw_code().split(","));
        String[] drawCode = ticket.getDraw_code().split(",");
        data.put("isDraw", false);
        data.put("countDownArr", new ArrayList());
        data.put("countDownHours", "");
        data.put("countDownMinutes", "");
        data.put("countDownSeconds", "");
        int[] intCode = lotteryCodeAdapter.toCalculate(ticket.getDraw_code());
        ArrayList DT = (ArrayList) lotteryUtils.DragonTiger(intCode);
        switch (type) {
            //PK10
            case 1:
                data.put("dragonTigerArr", DT);
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("sixthNumber", drawCode[5]);
                data.put("seventhNumber", drawCode[6]);
                data.put("eighthNumber", drawCode[7]);
                data.put("ninthNumber", drawCode[8]);
                data.put("tenthNumber", drawCode[9]);
                data.put("firstDragonTiger", DT.get(0));
                data.put("secondDragonTiger", DT.get(1));
                data.put("thirdDragonTiger", DT.get(2));
                data.put("fourthDragonTiger", DT.get(3));
                data.put("fifthDragonTiger", DT.get(4));
                data.put("championRunnerSum", lotteryUtils.sum_FS(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                break;
            //时时彩
            case 2:
                data.put("dragonTiger", DT.get(0));
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("firstThree", lotteryUtils.threeFMB(intCode)[0]);
                data.put("middleThree", lotteryUtils.threeFMB(intCode)[1]);
                data.put("lastThree", lotteryUtils.threeFMB(intCode)[2]);
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                break;
            //11选5
            case 3:
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("firstThree", lotteryUtils.threeFMB(intCode)[0]);
                data.put("middleThree", lotteryUtils.threeFMB(intCode)[1]);
                data.put("lastThree", lotteryUtils.threeFMB(intCode)[2]);
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                break;
            //快乐十分
            case 4:
                data.put("dragonTigerArr", DT);
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("sixthNumber", drawCode[5]);
                data.put("seventhNumber", drawCode[6]);
                data.put("eighthNumber", drawCode[7]);
                data.put("firstDragonTiger", DT.get(0));
                data.put("secondDragonTiger", DT.get(1));
                data.put("thirdDragonTiger", DT.get(2));
                data.put("fourthDragonTiger", DT.get(3));
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                data.put("tailBigSmall", lotteryUtils.tail_BS(intCode));
                break;
            //快三
            case 5:
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("firstFcru", drawCode[0]);
                data.put("secondFcru", drawCode[1]);
                data.put("thirdFcru", drawCode[2]);
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                break;
            //重庆农场
            case 6:
                data.put("dragonTigerArr", DT);
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("sixthNumber", drawCode[5]);
                data.put("seventhNumber", drawCode[6]);
                data.put("eighthNumber", drawCode[7]);
                data.put("firstDragonTiger", DT.get(0));
                data.put("secondDragonTiger", DT.get(1));
                data.put("thirdDragonTiger", DT.get(2));
                data.put("fourthDragonTiger", DT.get(3));
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode));
                data.put("tailBigSmall", lotteryUtils.tail_BS(intCode));
                break;
             //排列3
            case 7:
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("hundredTenSum", lotteryUtils.hundredTenSum(intCode));
                data.put("hundredTenSingleEven", lotteryUtils.hundredTenSingleEven(intCode));
                data.put("hundredTenBigSmall", lotteryUtils.hundredTenBigSmall(intCode));
                data.put("hundredOneSum", lotteryUtils.hundredOneSum(intCode));
                data.put("hundredOneSingleEven", lotteryUtils.hundredOneSingleEven(intCode));
                data.put("hundredOneBigSmall", lotteryUtils.hundredTenBigSmall(intCode));
                data.put("tenOneSum", lotteryUtils.TenOneSum(intCode));
                data.put("tenOneSingleEven", lotteryUtils.TenOneSingleEven(intCode));
                data.put("tenOneBigSmall", lotteryUtils.TenOneBigSmall(intCode));
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("drawSumSingleEven", lotteryUtils.sum_SD(intCode));
                data.put("drawSumBigSmall", lotteryUtils.sum_BS(intCode));
                break;


        }
        return (HashMap<Object, Object>) data;
    }


}