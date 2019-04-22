package com.hash.by_lottery.component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hash.by_lottery.log.LogUtils;
import com.hash.by_lottery.utils.lotteryUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Scheduled(cron = "0 0 0,6,12,18 * * ?")
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



}
