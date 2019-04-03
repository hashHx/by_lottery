package com.hash.by_lottery.Service;


import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;

public interface ConfigService {

    JSONObject getConfig();

    JSONArray getConfigById(int id);

    int updateConfigById(int id);


}
