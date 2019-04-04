package com.hash.by_lottery.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.controller.LotteryTicketController;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    //@Scheduled(fixedDelay = 30000)
    public void getSIXSUMHistory() {
        if (LotteryTicketController.saveSpace.INSTANCE.getValue().get("ticket") == null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<JSONObject> response = restTemplate.exchange("https://1680660.com/smallSixMobile/findSmallSixHistory.do?year=2019", HttpMethod.GET, entity, JSONObject.class);
            JSONObject body = response.getBody().getJSONObject("result");
            JSONArray dataArray = body.getJSONArray("data");
            JSONObject firstData = (JSONObject) dataArray.get(0);
            if (LotteryTicketController.saveSpace.INSTANCE.getValue() == null) {
                JSONObject newValue = LotteryTicketController.saveSpace.INSTANCE.getValue();
                newValue.put("tList", dataArray);
                newValue.put("ticket", firstData);
                //logger.trace("六合彩数据创建，时间：" + System.currentTimeMillis());
                System.out.println("六合彩数据创建，时间：" + System.currentTimeMillis());
            } else {

                if (LotteryTicketController.saveSpace.INSTANCE.getValue().get("ticket") != firstData.get("ticket")) {
                    JSONObject newValue = new JSONObject();
                    newValue.put("tList", dataArray);
                    newValue.put("ticket", firstData);
                    LotteryTicketController.saveSpace.INSTANCE.setValue(newValue);
                    // logger.trace("六合彩数据更新，时间：" + System.currentTimeMillis());
                    System.out.println("六合彩数据更新，时间：" + System.currentTimeMillis());
                }
            }
        }
    }
}
