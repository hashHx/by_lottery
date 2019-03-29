package com.hash.by_lottery.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ConfigService;
import com.hash.by_lottery.dao.BannerDao;
import com.hash.by_lottery.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ConfigServiceImpl
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/29 19:14
 * @Version 1.0
 **/

@Service
public class ConfigServiceImpl implements ConfigService {

    private static String CustomerServiceUrl = "https://static.meiqia.com/dist/standalone.html?_=t&eid=53419";

    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private NoticeDao noticeDao;


    @Override
    public JSONObject getConfig() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Banners",bannerDao.getBanners());
        jsonObject.put("Notices",noticeDao.getNotices());
        jsonObject.put("CustomerServiceUrl",CustomerServiceUrl);
        return jsonObject;
    }

}
