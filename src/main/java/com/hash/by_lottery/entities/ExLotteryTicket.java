package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ExLotteryTicket
 * @Descritption 拓展彩票类
 * @Author Hash
 * @Date 2019/3/22 13:25
 * @Version 1.0
 **/

@Data
@Getter
@Setter
public class ExLotteryTicket  {

    private Long l_id;
    private String lot_name;
    private int lot_count;
    private int lot_interval;
    private int lot_type;
    private String lot_videoUrl;
    private String lot_videoImg;
    private String lot_imgUrl;
    private String lot_code;
    private String draw_code;
    private String draw_issue;
    private String draw_time;
    private int lot_state;
    private int is_hot;

}
