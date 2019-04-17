package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName BaseLotteryTicketDao
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:17
 * @Version 1.0
 **/

@Mapper
public interface BaseLotteryTicketDao {

     BaseLotteryTicket findByLotCodeAndIssue(String code, String issue);
     int insertInfo_spider(BaseLotteryTicket ticket);
     int insertInfo_push(BaseLotteryTicket ticket);
     int getCurrentCount(String lot_code);
     int findLotCode(String lot_code);
     BaseLotteryTicket getNewTicketInfo(String lot_code);
     Long getFirstIssue(String lot_code);


}

