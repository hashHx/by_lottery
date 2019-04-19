package com.hash.by_lottery.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ConfigDao
 * @Descritption 配置类
 * @Author Hash
 * @Date 2019/4/3 12:13
 * @Version 1.0
 **/


@Data
@Accessors(chain = true)
public class Config  implements Serializable {

    private int config_id;
    private String config_theme;
    private String config_content;


}
