package com.hash.by_lottery.controller;


import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ConfigService;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName lotterTickerController
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:33
 * @Version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/Config",method = RequestMethod.GET)
    public JSONObject getConfig() {
        return configService.getConfig();
    }


    @RequestMapping(value = "/config/webSiteInfo",method = RequestMethod.GET)
    public net.sf.json.JSONObject getWebSiteInfo(){
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        net.sf.json.JSONObject json = configService.getConfigById(2).getJSONObject(0);
        if (json!=null){
            jsonObject.put("error_code",0);
            jsonObject.put("data",json);
        }else {
            jsonObject.put("error_code",1);
        }
        return jsonObject;
    }

    @RequestMapping(value = "/config/webSiteInfo/1",method = RequestMethod.GET)
    public int updateWebSiteInfo(){

        return configService.updateConfigById(2,null);
    }

    @RequestMapping(value = "/config/gameDetail/3",method = RequestMethod.GET)
    public JSONArray updateGameDetail(){
        return configService.getConfigById(3);
    }


}
