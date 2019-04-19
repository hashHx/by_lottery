package com.hash.by_lottery.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class BaseLotteryTicket implements Serializable {

    private Long l_id;
    private String lot_code;
    private String draw_code;
    private String draw_issue;
    private String draw_time;


}
