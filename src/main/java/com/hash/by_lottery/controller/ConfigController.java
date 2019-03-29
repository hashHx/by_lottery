package com.hash.by_lottery.controller;


import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ConfigService;
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
}
