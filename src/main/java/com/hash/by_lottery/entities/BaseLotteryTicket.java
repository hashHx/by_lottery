package com.hash.by_lottery.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class BaseLotteryTicket {

    private Long l_id;
    private String lot_code;
    private String draw_code;
    private String draw_issue;
    private String draw_time;


}
