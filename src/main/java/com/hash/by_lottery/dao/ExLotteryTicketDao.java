package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Stack;

/**
 * @ClassName BaseLotteryTicketDao
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:17
 * @Version 1.0
 **/

@Mapper
public interface ExLotteryTicketDao {

      List<ExLotteryTicket> stuffInfoToExLotteryTicket(String lot_code);
      ExLotteryTicket getNewTicketInfo(String lot_code);
      List<ExLotteryTicket> getTicketInfoList(String lot_code);
      List<ExLotteryTicket> getTicketInfoList_(String lot_code);
      List<ExLotteryTicket> getTicketInfoListWithTime(String lot_code,String draw_time);
      List<ExLotteryTicket> getLimitTicketList(@Param("lot_code") String lot_code, @Param("limit") int limit);
      ExLotteryTicket getTicketInfoByIssue(String lot_code,String draw_issue);
      List<ExLotteryTicket> getTicketTypeInfo();
      List<ExLotteryTicket> getTicketInfoDoubleList(String lot_code);

}

