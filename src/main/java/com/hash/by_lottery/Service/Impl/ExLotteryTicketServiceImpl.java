package com.hash.by_lottery.Service.Impl;


import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.dao.ExLotteryTicketDao;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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





}
