package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
      List<ExLotteryTicket> getTicketInfoListWithTime(String lot_code,String draw_time);

}

