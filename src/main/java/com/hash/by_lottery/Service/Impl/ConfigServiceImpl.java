package com.hash.by_lottery.Service.Impl;



import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ConfigService;
import com.hash.by_lottery.dao.BannerDao;
import com.hash.by_lottery.dao.ConfigDao;
import com.hash.by_lottery.dao.NoticeDao;
import com.hash.by_lottery.entities.Config;
import net.sf.json.JSONArray;
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
    @Autowired
    private ConfigDao configDao;


    @Override
    public com.alibaba.fastjson.JSONObject getConfig() {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("Banners", bannerDao.getBanners());
        jsonObject.put("Notices", noticeDao.getNotices());
        jsonObject.put("CustomerServiceUrl", CustomerServiceUrl);
        return jsonObject;
    }

    @Override
    public JSONArray getConfigById(int id) {
        String s = configDao.getConfigById(id);
        if (s.startsWith("[")) {
            return JSONArray.fromObject(s);
        } else {
            s = "[" + s + "]";
            return JSONArray.fromObject(s);
        }

    }

    @Override
    public int updateConfigById(int id, com.alibaba.fastjson.JSONObject content) {
        Config config = new Config();
        config.setConfig_id(id);
        //config.setConfig_content(updateConfigContentSwitch(id));
        configDao.updateConfigById(config);
        return 1;
    }



//    public String updateConfigContentSwitch(int id) {
//        switch (id) {
//            case 1:
//                //JSONArray array = this.getConfigById(id)
//                return null;
//            case 2:
//                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
//                jsonObject.put("webName", "彩票开奖网");
//                jsonObject.put("webLogo", "https://xxxx.xxx.xxx/img/logo.png");
//                jsonObject.put("webCopyright", "版权信息");
//                jsonObject.put("webBackup", "备案信息");
//                jsonObject.put("appRQcode", "https://xxxx.xxx.xxx/img/logo.png");
//                return jsonObject.toJSONString();
//            case 4:

//                com.alibaba.fastjson.JSONArray.parseObject(array);
//                return null;
//
//    }


}
