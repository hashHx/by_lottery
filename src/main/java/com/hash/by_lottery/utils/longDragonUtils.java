package com.hash.by_lottery.utils;

import com.alibaba.fastjson.JSONArray;
import com.hash.by_lottery.dao.BaseLotteryTicketDao;
import com.hash.by_lottery.dao.ExLotteryTicketDao;
import com.hash.by_lottery.entities.ExLotteryTicket;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName longDragonUtils
 * @Descritption 长龙玩法工具类
 * @Author Hash
 * @Date 2019/3/27 20:37
 * @Version 1.0
 **/

public class longDragonUtils {

    @Autowired
    private BaseLotteryTicketDao dao;

    @Autowired
    private ExLotteryTicketDao dao_ex;

    private volatile static longDragonUtils instance = null;

    private longDragonUtils() {


    }

    public static longDragonUtils getInstance() {

        if (instance == null) {
            synchronized (longDragonUtils.class) {
                if (instance == null) {
                    instance = new longDragonUtils();
                }
            }
        }
        return instance;
    }

    private String[] lotCodes = {"10001", "10057"};


    @Data
    @Accessors(chain = true)
    public class data {
        private int rank;
        private int state;
        private int count;
    }

    public ArrayList getData() {
        ArrayList list = new ArrayList();
        ExLotteryTicket newTicket = dao_ex.getNewTicketInfo("10001");
        int type = newTicket.getLot_type();
        int issue = Integer.parseInt(newTicket.getDraw_issue());
        return null;
    }

    public JSONArray getDataAll() {
        return null;
    }


    private static List countRankAndState(int type, ExLotteryTicket ticket, ExLotteryTicket preTicket) {

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
        List<Boolean> countState_7 = new ArrayList<>(); //和

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
                }else {
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
                }else {
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
                }else {
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
                }else {
                    countState_4.add(false);
                }

                //龙
                for (int i = 0; i < DT.size(); i++) {
                    if (DT.get(i) =="0" && DT_pre.get(i)=="0") {
                        countState_5.add(true);
                    } else {
                        countState_5.add(false);
                    }
                }

                //虎
                for (int i = 0; i < DT.size(); i++) {
                    if (DT.get(i) =="1" && DT_pre.get(i)=="1") {
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
                if (lotteryUtils.sum_FS(code) % 2 != 0 && lotteryUtils.sum_FS(code_pre) % 2 != 0) {
                    countState_1.add(true);
                }else {
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
                if (lotteryUtils.sum_FS(code) % 2 == 0 && lotteryUtils.sum_FS(code_pre) % 2 == 0) {
                    countState_2.add(true);
                }else {
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
                if (lotteryUtils.sum_FS(code) > 22 && lotteryUtils.sum_FS(code_pre) > 22) {
                    countState_3.add(true);
                }else {
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
                if (lotteryUtils.sum_FS(code) <= 22 && lotteryUtils.sum_FS(code_pre) <= 22) {
                    countState_4.add(true);
                }else {
                    countState_4.add(false);
                }

                //龙

                    if (DT.get(0) == "0" && DT_pre.get(0)=="0") {
                        countState_5.add(true);
                    } else {
                        countState_5.add(false);
                }

                //虎

                    if (DT.get(0) == "1" && DT_pre.get(0)=="1") {
                        countState_6.add(true);
                    } else {
                        countState_6.add(false);
                    }
                //和

                if (DT.get(0) == "2" && DT_pre.get(0)=="2") {
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






        }
        return countStates;
    }

}

