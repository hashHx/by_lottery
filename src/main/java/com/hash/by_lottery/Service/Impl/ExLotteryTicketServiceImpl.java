package com.hash.by_lottery.Service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.dao.ExLotteryTicketDao;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ExLotteryTickerServiceImpl
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:30
 * @Version 1.0
 **/

@Service
public class ExLotteryTicketServiceImpl implements ExLotteryTicketService {


    @Autowired
    private ExLotteryTicketDao dao;


    @Override
    public List<ExLotteryTicket> stuffTickerInfo(String code) {

        return dao.stuffInfoToExLotteryTicket(code);
    }


    public ExLotteryTicket getNewTicketInfo(String code) {
        return dao.getNewTicketInfo(code);
    }

    @Override
    public List<ExLotteryTicket> getTicketList(String code) {
        return dao.getTicketInfoList(code);
    }

    @Override
    public List<ExLotteryTicket> getTicketListWithTime(String code,String time) {
        return dao.getTicketInfoListWithTime(code, time);
    }

    @Override
    public List<JSONObject> getLimitTicketList(String code, int limit) {

        Stack<ExLotteryTicket> list = new Stack<>();

        List<String> list_issue =  new ArrayList<>();

        List<JSONObject> list_code =  new ArrayList<>();
        List<String> list_code_all =  new ArrayList<>();

        limit = 25;
        

        list =  dao.getLimitTicketList(code,limit);

        //获取期数列表
        for (ExLotteryTicket t: list
             ) {
            System.out.println(t.getDraw_issue());
            list_issue.add(t.getDraw_issue());
        }

        ExLotteryTicket t = list.get(0);
        JSONObject jsonObject = new JSONObject();

        //
        int length = t.getDraw_code().split(",").length;
        int index = limit;
        for (int i = 0; i < length; i++) {
            String[] str = new String[index];

            for (int j = 0; j < index; j++) {
                str[j] = list.get(j).getDraw_code().split(",")[i];
            }

            jsonObject.put("issue",list_issue);
            jsonObject.put("code",str);
//            map.put("issue",list_issue);
//            map.put("code",str);
           // System.out.println(jsonObject.toString());
            list_code.add(jsonObject);
            System.out.println(list_code.get(i));
            jsonObject.clear();
        }

        return list_code;
    }


}
