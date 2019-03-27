package com.hash.by_lottery.Service;

import com.hash.by_lottery.entities.ExLotteryTicket;

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

    List<ExLotteryTicket> getTicketListWithTime(String code,String time);

}
