package com.hash.by_lottery.Service.Impl;

import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.dao.BaseLotteryTicketDao;
import com.hash.by_lottery.entities.BaseLotteryTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BaseLotteryTickerServiceImpl
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:30
 * @Version 1.0
 **/

@Service
public class BaseLotteryTicketServiceImpl implements BaseLotteryTicketService {

    @Autowired
    private BaseLotteryTicketDao dao;

    @Override
    public BaseLotteryTicket getTickerInfo(String code, String issue) {

        return dao.findByLotCodeAndIssue(code, issue);

    }

    @Override
    public int saveBaseTicketInfo(BaseLotteryTicket ticket) {
        return dao.insertInfo_spider(ticket);
    }

    @Override
    public int saveBaseTicketInfoFromPush(BaseLotteryTicket ticket) {
        return dao.insertInfo_push(ticket);
    }

    @Override
    public String getCurrentCount(String lotCode) {
        if (dao.getCurrentCount(lotCode)!=null){
            return dao.getCurrentCount(lotCode);
        }else {
            return "1";
        }
    }

    @Override
    public int findCode(String code) {
        return dao.findLotCode(code);
    }
}
