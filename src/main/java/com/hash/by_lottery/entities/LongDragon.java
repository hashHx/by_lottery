package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName LongDragon
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/2 12:35
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
public class LongDragon implements Serializable {

    private int rank;
    private int state;
    private int count;
}
