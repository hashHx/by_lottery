package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.Config;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ConfigDao
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/3 12:19
 * @Version 1.0
 **/


@Mapper
public interface ConfigDao {

    String getConfigById(int config_id);
    int updateConfigById(Config config);

}
