package com.hash.by_lottery.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hash.by_lottery.log.LogUtils;
import com.hash.by_lottery.utils.lotteryUtils;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MissionComponent
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/4 12:41
 * @Version 1.0
 **/
@Component
@Service
public class MissionComponent {


    static Logger logger = LogUtils.getBussinessLogger();

    static ArrayList<String> dateList = new ArrayList();

    static {
        if (dateList != null) {
            dateList= new ArrayList();
        }
        MarkSixDate();
    }

    @Scheduled(cron = "0 0 0,6,13,18 * * ?")
    public static void MarkSixDate() {
        if (dateList != null) {
            dateList= new ArrayList();
        }

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false); // 取消 CSS 支持 ✔
        webClient.getOptions().setJavaScriptEnabled(false); // 取消 JavaScript支持 ✔
        try {
            HtmlPage page = webClient.getPage("https://www.kjrq.tv/"); // 解析获取页面

            List<HtmlElement> testList = (page.getByXPath("//li[@class='kjr  ']"));

            for (int i = 0; i < testList.size(); i++) {
                dateList.add(dateFormat(testList.get(i).getOnClickAttribute().split("\"")[1]));
            }


        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.close(); // 关闭客户端，释放内存
            logger.info("更新六合彩开奖日期:" + dateList);
        }
    }

    private static String dateFormat(String date) {
        String[] tmp = date.split("-");
        if (tmp[1].length() == 1) {
            tmp[1] = "0" + tmp[1];
        }
        if (tmp[2].length() == 1) {
            tmp[2] = "0" + tmp[2];
        }
        return tmp[0] + "-" + tmp[1] + "-" + tmp[2] + " 21:30:00";
    }

    public static Long getNearDate() {
        Long currentTime = System.currentTimeMillis();
        for (String s :
                dateList) {
            if (Long.parseLong(lotteryUtils.date2TimeStamp(s)) - currentTime > 0) {
                return Long.parseLong(lotteryUtils.date2TimeStamp(s));
            }
        }
        return 0L;
    }


    @Scheduled(cron = "0 55 17,23 * * ?")
    public static void BJSC_supplement() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String currentDate = (lotteryUtils.timeStamp2Date(String.valueOf(System.currentTimeMillis()),null)).substring(0,9);
        ResponseEntity<JSONObject> response = restTemplate.exchange("https://api.api68.com/pks/getPksHistoryList.do?date="+currentDate+"&lotCode=10001", HttpMethod.GET, entity, JSONObject.class);
        JSONObject body = response.getBody().getJSONObject("result");
        JSONArray dataArray = body.getJSONArray("data");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        for (Object ob :
                dataArray) {
            JSONObject jsonObject = (JSONObject) ob;

//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//  也支持中文
            params.add("lot_code", "10001");
            params.add("draw_code", jsonObject.getString("preDrawCode"));
            params.add("draw_issue", jsonObject.getString("preDrawIssue"));
            params.add("draw_time", jsonObject.getString("preDrawTime"));
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//  执行HTTP请求
            ResponseEntity<String> response_post = restTemplate.exchange("https://s-api.lottwest.com/lottery/spider", HttpMethod.POST, requestEntity, String.class);
//  输出结果
            logger.info("北京赛车补期"+response_post.getBody());
        }
    }



}
