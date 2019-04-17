package com.hash.by_lottery.Service;

import com.hash.by_lottery.entities.BaseLotteryTicket;

/**
 * @ClassName BaseLotteryTicketService
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:28
 * @Version 1.0
 **/

public interface BaseLotteryTicketService {

    BaseLotteryTicket getTickerInfo(String code, String issue);
    int saveBaseTicketInfo(BaseLotteryTicket ticket);
    int saveBaseTicketInfoFromPush(BaseLotteryTicket ticket);
    int getCurrentCount(String lotCode);
    int findCode(String code);
    Long getFirstIssue(String lot_code);

}
