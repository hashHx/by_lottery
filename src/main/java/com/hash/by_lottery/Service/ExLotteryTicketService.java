package com.hash.by_lottery.Service;

import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.entities.ticketPlan;

import java.util.List;

/**
 * @ClassName BaseLotteryTicketService
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:28
 * @Version 1.0
 **/

public interface ExLotteryTicketService {

    List<ExLotteryTicket> stuffTickerInfo(String code);

    ExLotteryTicket getNewTicketInfo(String code);

    List<ExLotteryTicket> getTicketList(String code);

    List<ExLotteryTicket> getTicketListWithLimit(String code, int limit);

    List<ExLotteryTicket> getTicketListWithTime(String code,String time);

    List<JSONObject> getLimitTicketList(String code, int limit);

    List getLongDragonInfo(String lot_code);

    JSONObject getTicketTypeInfo();

    List<ExLotteryTicket> getTicketInfoDoubleList(String lotCode);

    List<ExLotteryTicket> getIndexTickets(List<String> list);

    void insertCache(ExLotteryTicket ex);
    int getCacheCount(String lot_code);

//    ticketPlan getPlanWithLotCode(String lot_code);

}
