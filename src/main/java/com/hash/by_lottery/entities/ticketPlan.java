package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ticketPlan
 * @Descritption 记录每个彩种第一期，最后一期期号
 * @Author Hash
 * @Date 2019/4/13 21:00
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
public class ticketPlan {

    private String lot_code;
    private String start_issue;
    private String end_issue;
    private String lot_count;



}
