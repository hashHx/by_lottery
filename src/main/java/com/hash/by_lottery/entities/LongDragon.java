package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName LongDragon
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/2 12:35
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
public class LongDragon {

    private int rank;
    private int state;
    private int count;
}
