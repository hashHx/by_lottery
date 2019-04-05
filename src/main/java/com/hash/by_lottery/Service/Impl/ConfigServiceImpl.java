package com.hash.by_lottery.Service.Impl;



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
//            case 3:
//                com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject();
//                ArrayList<JSONObject> array = new ArrayList<>();
//                com.alibaba.fastjson.JSONObject temp = new com.alibaba.fastjson.JSONObject();
//                temp.put("1","开奖号码");
//                array.add(temp);
//                com.alibaba.fastjson.JSONObject temp1 = new com.alibaba.fastjson.JSONObject();
//                temp1.put("2","双面统计");
//                array.add(temp1);
//                com.alibaba.fastjson.JSONObject temp2 = new com.alibaba.fastjson.JSONObject();
//                temp2.put("3","长龙统计");
//                array.add(temp2);
//                com.alibaba.fastjson.JSONObject temp3 = new com.alibaba.fastjson.JSONObject();
//                temp3.put("4","单双大小历史");
//                array.add(temp3);
//                com.alibaba.fastjson.JSONObject temp4 = new com.alibaba.fastjson.JSONObject();
//                temp4.put("5","单双大小路珠");
//                array.add(temp4);
//                com.alibaba.fastjson.JSONObject temp5 = new com.alibaba.fastjson.JSONObject();
//                temp5.put("6","历史号码统计");
//                array.add(temp5);
//                com.alibaba.fastjson.JSONObject temp6 = new com.alibaba.fastjson.JSONObject();
//                temp6.put("7","龙虎走势");
//                array.add(temp6);
//                com.alibaba.fastjson.JSONObject temp7 = new com.alibaba.fastjson.JSONObject();
//                temp7.put("8","号码路珠");
//                array.add(temp7);
//                com.alibaba.fastjson.JSONObject temp8 = new com.alibaba.fastjson.JSONObject();
//                temp8.put("9","冷热分析");
//                array.add(temp8);
//                com.alibaba.fastjson.JSONObject temp9 = new com.alibaba.fastjson.JSONObject();
//                temp9.put("10","路珠分析");
//                array.add(temp9);
//                com.alibaba.fastjson.JSONObject temp10 = new com.alibaba.fastjson.JSONObject();
//                temp10.put("11","总和路珠");
//                array.add(temp10);
//                com.alibaba.fastjson.JSONObject temp11 = new com.alibaba.fastjson.JSONObject();
//                temp11.put("12","号码统计");
//                array.add(temp11);
//                com.alibaba.fastjson.JSONObject temp12 = new com.alibaba.fastjson.JSONObject();
//                temp12.put("13","形态走势");
//                array.add(temp12);
//                com.alibaba.fastjson.JSONObject temp13 = new com.alibaba.fastjson.JSONObject();
//                temp13.put("14","基本走势");
//                array.add(temp13);
//                com.alibaba.fastjson.JSONObject temp14 = new com.alibaba.fastjson.JSONObject();
//                temp14.put("15","定位走势");
//                array.add(temp14);
//                jsonObject1.put("2",array);
//                ArrayList<JSONObject> array1 = new ArrayList<>();
//                com.alibaba.fastjson.JSONObject temp_pk10 = new com.alibaba.fastjson.JSONObject();
//                temp_pk10.put("1","开奖号码");
//                array1.add(temp_pk10);
//                com.alibaba.fastjson.JSONObject temp_pk101 = new com.alibaba.fastjson.JSONObject();
//                temp_pk101.put("2","双面统计");
//                array1.add(temp_pk101);
//                com.alibaba.fastjson.JSONObject temp_pk102 = new com.alibaba.fastjson.JSONObject();
//                temp_pk102.put("3","长龙统计");
//                array1.add(temp_pk102);
//                com.alibaba.fastjson.JSONObject temp_pk103 = new com.alibaba.fastjson.JSONObject();
//                temp_pk103.put("4","单双大小历史");
//                array1.add(temp_pk103);
//                com.alibaba.fastjson.JSONObject temp_pk104 = new com.alibaba.fastjson.JSONObject();
//                temp_pk104.put("5","单双大小路珠");
//                array1.add(temp_pk104);
//                com.alibaba.fastjson.JSONObject temp_pk105 = new com.alibaba.fastjson.JSONObject();
//                temp_pk105.put("6","历史号码统计");
//                array1.add(temp_pk105);
//                com.alibaba.fastjson.JSONObject temp_pk106 = new com.alibaba.fastjson.JSONObject();
//                temp_pk106.put("7","龙虎统计");
//                array1.add(temp_pk106);
//                com.alibaba.fastjson.JSONObject temp_pk107 = new com.alibaba.fastjson.JSONObject();
//                temp_pk107.put("18","号码前后路珠");
//                array1.add(temp_pk107);
//                com.alibaba.fastjson.JSONObject temp_pk108 = new com.alibaba.fastjson.JSONObject();
//                temp_pk108.put("9","冷热分析");
//                array1.add(temp_pk108);
//                com.alibaba.fastjson.JSONObject temp_pk109 = new com.alibaba.fastjson.JSONObject();
//                temp_pk109.put("10","路珠分析");
//                array1.add(temp_pk109);
//                com.alibaba.fastjson.JSONObject temp_pk1010 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1010.put("23","龙虎路珠");
//                array1.add(temp_pk1010);
//                com.alibaba.fastjson.JSONObject temp_pk1011 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1011.put("30","今日号码统计");
//                array1.add(temp_pk1011);
//                com.alibaba.fastjson.JSONObject temp_pk1012 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1012.put("19","冠亚和路珠");
//                array1.add(temp_pk1012);
//                com.alibaba.fastjson.JSONObject temp_pk1013 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1013.put("20","冠亚和走势");
//                array1.add(temp_pk1013);
//                com.alibaba.fastjson.JSONObject temp_pk1014 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1014.put("24","冠亚和两面历史");
//                array1.add(temp_pk1014);
//                com.alibaba.fastjson.JSONObject temp_pk1015 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1015.put("22","号码规律统计");
//                array1.add(temp_pk1015);
//                com.alibaba.fastjson.JSONObject temp_pk1016 = new com.alibaba.fastjson.JSONObject();
//                temp_pk1016.put("15","定位走势");
//                array1.add(temp_pk1016);
//                jsonObject1.put("1",array1);
//                return jsonObject1.toJSONString();
//
//
//        }
//        return null;
//    }

}
