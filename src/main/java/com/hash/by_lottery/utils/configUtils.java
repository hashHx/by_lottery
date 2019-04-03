package com.hash.by_lottery.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @ClassName configUtils
 * @Descritption 配置工具类
 * @Author Hash
 * @Date 2019/4/3 12:40
 * @Version 1.0
 **/

public class configUtils {

    public static JSONArray String2Json(String content){
        return JSONArray.fromObject(content);
    }


}
