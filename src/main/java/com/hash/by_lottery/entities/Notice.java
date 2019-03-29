package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Notice
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/29 18:55
 * @Version 1.0
 **/


@Data
@Accessors(chain = true)
public class Notice {



    private int id;
    private int Sort;
    private int isNotice;
    private int showType;
    private String contentType;
    private String content;



}
