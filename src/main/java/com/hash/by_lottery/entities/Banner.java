package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Banner
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/29 18:51
 * @Version 1.0
 **/


@Data
@Accessors(chain = true)
public class Banner {

    private int id;
    private int Sort;
    private String imgUrl;
    private int showType;


}
