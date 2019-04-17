package com.hash.by_lottery.utils;

import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.Service.ExLotteryTicketService;
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


    public static HashMap<Object, Object> getresult(ExLotteryTicket ticket, int type, ExLotteryTicketService service, BaseLotteryTicketService service_base) {
        Map data = new HashMap();
        data.put("lotCode", ticket.getLot_code());
        data.put("lotName", ticket.getLot_name());
        data.put("serverTime", System.currentTimeMillis());

        data.put("lotType", ticket.getLot_type());
        data.put("preDrawIssue", ticket.getDraw_issue());
        data.put("drawIssue", String.valueOf(Long.valueOf(ticket.getDraw_issue()) + 1));
        Long time = Long.parseLong(lotteryUtils.date2TimeStamp(ticket.getDraw_time()));
        data.put("preDrawTime", time);
        data.put("nextDrawTime", String.valueOf(time + (ticket.getLot_interval() * 1000)+1500));
        Long issue = Long.parseLong(ticket.getDraw_issue());
        //Long issue_ = service.getPlanWithLotCode(ticket.getLot_code()).getEnd_issue()== null? 0L:Long.parseLong(service.getPlanWithLotCode(ticket.getLot_code()).getEnd_issue());
        //Long count = new Long(ticket.getLot_count());
        int currentCount = service_base.getCurrentCount(ticket.getLot_code());
        data.put("currentCount",currentCount);
        data.put("surplusCount",ticket.getLot_count()-currentCount);
        //if (issue_!=0L)//{
        if (currentCount==issue) {
            //本期为最后一期
            JSONObject jsonObject = getClosedList();
            Long closing =  jsonObject.get(ticket.getLot_code())==null?0L:(Long)jsonObject.get(ticket.getLot_code());
            if (closing != 0L) {
                data.replace("drawIssue", "");
                data.put("nextDrawTime", String.valueOf(time + closing));
            }

        }
    //}
        if (ticket.getLot_code().equals("10002")) {

            if (currentCount == 9L) {
                data.put("nextDrawTime", String.valueOf(time + 15600000L));
            }
        }
        data.put("imgUrl", ticket.getLot_imgUrl());
        data.put("iconUrl", ticket.getLot_iconUrl());
        data.put("videoImg", ticket.getLot_videoImg());
        data.put("videoUrl", ticket.getLot_videoUrl());
        data.put("totalCount", ticket.getLot_count());
        //data.put("drawNumberArr", ticket.getDraw_code().split(","));
        data.put("drawNumberArr", lotteryCodeAdapter.toCalculate(ticket.getDraw_code()));
        String[] drawCode = ticket.getDraw_code().split(",");
        data.put("isDraw", false);
        data.put("lotState", ticket.getLot_state());
        data.put("isHot", ticket.getIs_hot());
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
                data.put("singleOrEven", lotteryUtils.FS_sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
                break;
            //时时彩
            case 2:
                data.put("dragonTigerArr", DT);
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
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
                break;
            //11选5
            case 3:
                data.put("firstNumber", drawCode[0]);
                data.put("secondNumber", drawCode[1]);
                data.put("thirdNumber", drawCode[2]);
                data.put("fourthNumber", drawCode[3]);
                data.put("fifthNumber", drawCode[4]);
                data.put("dragonTigerArr", lotteryUtils.DragonTiger(intCode));
                data.put("firstThree", lotteryUtils.threeFMB(intCode)[0]);
                data.put("middleThree", lotteryUtils.threeFMB(intCode)[1]);
                data.put("lastThree", lotteryUtils.threeFMB(intCode)[2]);
                data.put("drawSum", lotteryUtils.sum_All(intCode));
                data.put("singleOrEven", lotteryUtils.sum_SD(intCode));
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
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
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
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
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
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
                data.put("bigOrSmall", lotteryUtils.sum_BS(intCode, type));
                data.put("tailBigSmall", lotteryUtils.tail_BS(intCode));
                break;
            //排列3(MARK6)
            case 7:
//                data.put("firstNumber", drawCode[0]);
//                data.put("secondNumber", drawCode[1]);
//                data.put("thirdNumber", drawCode[2]);
//                data.put("hundredTenSum", lotteryUtils.hundredTenSum(intCode));
//                data.put("hundredTenSingleEven", lotteryUtils.hundredTenSingleEven(intCode));
//                data.put("hundredTenBigSmall", lotteryUtils.hundredTenBigSmall(intCode));
//                data.put("hundredOneSum", lotteryUtils.hundredOneSum(intCode));
//                data.put("hundredOneSingleEven", lotteryUtils.hundredOneSingleEven(intCode));
//                data.put("hundredOneBigSmall", lotteryUtils.hundredTenBigSmall(intCode));
//                data.put("tenOneSum", lotteryUtils.TenOneSum(intCode));
//                data.put("tenOneSingleEven", lotteryUtils.TenOneSingleEven(intCode));
//                data.put("tenOneBigSmall", lotteryUtils.TenOneBigSmall(intCode));
//                data.put("drawSum", lotteryUtils.sum_All(intCode));
//                data.put("drawSumSingleEven", lotteryUtils.sum_SD(intCode));
//                data.put("drawSumBigSmall", lotteryUtils.sum_BS(intCode, type));

                data.put("preDrawCode",ticket.getDraw_code());
                data.put("issue",ticket.getDraw_issue());
                data.put("color",Mark6Utils.getColors(lotteryCodeAdapter.toCalculate(ticket.getDraw_code())));
                data.put("czAndFe",Mark6Utils.getAnimals((lotteryCodeAdapter.toCalculate(ticket.getDraw_code()))));
                break;


        }
        return (HashMap<Object, Object>) data;
    }

    public static JSONObject getClosedList() {
        JSONObject object = new JSONObject();
        object.put("10001", 34800000L);
        object.put("10006", 36600000L);
        object.put("10005", 37200000L);
        object.put("10057", 32400000L);
        object.put("10034", 37200000L);
        object.put("10007", 38400000L);
        object.put("10026", 33300000L);
        object.put("10027", 39600000L);
        object.put("10028", 38400000L);
        object.put("10029", 43500000L);
        object.put("10030", 39000000L);
        object.put("10031", 37200000L);
        object.put("10032", 43200000L);
        object.put("10033", 34800000L);
        return object;
    }


}