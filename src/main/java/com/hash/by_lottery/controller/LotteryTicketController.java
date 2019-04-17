package com.hash.by_lottery.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.Service.ConfigService;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.Config;
import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.log.LogUtils;
import com.hash.by_lottery.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.security.DenyAll;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName lotterTickerController
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:33
 * @Version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LotteryTicketController {

    Logger logger = LogUtils.getBussinessLogger();

    @Autowired
    private BaseLotteryTicketService service;

    @Autowired
    private ExLotteryTicketService service_ex;

    @Autowired
    private ConfigService service_config;



    @RequestMapping(value = "/lottery/ticketInfo/{lotCode}/{lotIssue}", method = RequestMethod.GET)
    public String getTickerInfo(@PathVariable("lotCode") String lotCode, @PathVariable("lotIssue") String lotIssue) {

        return service.getTickerInfo(lotCode, lotIssue).toString();
    }


    @RequestMapping(value = "/lottery/spider", method = RequestMethod.POST)
    public String getSpiderInfo(BaseLotteryTicket ticket) {
        logger.info(ticket.toString());
        synchronized (this.getClass()) {
            if (ticket.getDraw_code() != ""||ticket.getDraw_code() != null) {
                BaseLotteryTicket check = service.getTickerInfo(ticket.getLot_code(), ticket.getDraw_issue());
                if (check != null) {
                    logger.error("重复信息" +
                            "——————"+ticket.toString());
                    return "二四你在逗我呢";
                }
                ticket.setL_id(IdGen.get().nextId());
                service.saveBaseTicketInfo(ticket);
                ExLotteryTicket newTicket = service_ex.getNewTicketInfo(ticket.getLot_code());
                service_ex.insertCache(newTicket);
                saveSpace.INSTANCE.setValue(newTicket.getLot_code(), newTicket);
                logger.trace(ticket.toString() + "spaceSave");
                return "ok";
            } else {
                logger.error("号码为空！————"+ticket.toString());
                return "空字符串，大佬";
            }
        }
    }

    @RequestMapping(value = "/lottery/ExTicketInfo/{lotCode}", method = RequestMethod.GET)
    public List<ExLotteryTicket> getExTicketInfo(@PathVariable("lotCode") String lotCode) {
        return service_ex.stuffTickerInfo(lotCode);
    }

    @RequestMapping(value = "/lottery/ExTicket/{lotCode}/{lotIssue}", method = RequestMethod.GET)
    public ExLotteryTicket getExTicker(@PathVariable("lotCode") String lotCode, @PathVariable("lotIssue") String lotIssue) {

        return service_ex.getNewTicketInfo(lotCode);
    }


    @RequestMapping(value = "lottery/InterfaceInfo", method = RequestMethod.POST)
    public String getInfoFromInterface(HttpServletRequest request) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Map<String, String[]> requestMsg = request.getParameterMap();

        for (String key : requestMsg.keySet()) {
            for (int i = 0; i < requestMsg.get(key).length; i++) {
                logger.trace(key);
                BaseLotteryTicket b = lotteryDecode.getBaseFromStore(key);
                logger.trace(b.toString());
                if (b != null) {
                    service.saveBaseTicketInfoFromPush(b);
                    return "ok";
                }
            }
        }

        return "error";
    }

    @RequestMapping(value = "lottery/gameDetail/{lot_type}")
    public Object getGameDetail(@PathVariable("lot_type") String lot_type) {
        net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) service_config.getConfigById(3).get(0);
        if (lot_type == "6") {
            return jsonObject.get("4");
        }
        return jsonObject.get(lot_type);
    }

    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/DrawNumber", method = RequestMethod.GET)
    public Object getExTickerInfo(@PathVariable("lotCode") String lotCode) {
        Map map = new HashMap();
        if (lotCode.equals("11009")) {
            return ResultGen.getResult(this.getSIXSUMHistoryList(), 0);
        }

        if (lotteryUtils.ResultByCodeVerification(service, service_ex, map, lotCode) != null) {
            List<ExLotteryTicket> list = service_ex.getTicketList(lotCode);
            List<Object> ticketList_ = new ArrayList();
            if (list != null) {
                for (ExLotteryTicket eticket : list
                ) {
                    map = ticketGen.getresult(eticket, eticket.getLot_type(),service_ex,service);
                    ticketList_.add(map);
                }
            } else {
                list = service_ex.getTicketListWithTime(lotCode,lotteryUtils.getpastDaysList(1).get(0));
                for (ExLotteryTicket eticket : list
                ) {
                    map = ticketGen.getresult(eticket, eticket.getLot_type(),service_ex,service);
                    ticketList_.add(map);
                }
            }
            return ResultGen.getResult(ticketList_, 0);
        } else {
            return lotteryUtils.ResultByCodeVerification(service, service_ex, map, lotCode);
        }
    }

    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/{time}", method = RequestMethod.GET)
    public HashMap<String, Object> getExTickerInfoWithTime(@PathVariable("lotCode") String lotCode, @PathVariable("time") String time) {

        Map map = new HashMap();
        if (lotteryUtils.CodeVerification(lotCode)) {
            //参数错误
            return ResultGen.getResult((HashMap<Object, Object>) map, 4);
        } else {
            if (service.findCode(lotCode) == 0) {
                //彩种不存在
                return ResultGen.getResult((HashMap<Object, Object>) map, 2);
            } else {
                List<ExLotteryTicket> list = service_ex.getTicketListWithTime(lotCode, time);
                List<Object> ticketList = new ArrayList();
                for (ExLotteryTicket e : list
                ) {
                    map = ticketGen.getresult(e, e.getLot_type(),service_ex,service);
                    ticketList.add(map);
                }
                return ResultGen.getResult(ticketList, 0);
            }
        }
    }


    @RequestMapping(value = "/lottery/TicketTrend/{lotCode}", method = RequestMethod.GET)
    public List<JSONObject> getTrendByLotCode(@PathVariable("lotCode") String lotCode) {
        return service_ex.getLimitTicketList(lotCode, 25);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JSONObject getSIXSUMHistory() {
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
        lotteryUtils.SIXSUM_utils(firstData);
        return firstData;
    }

    public ArrayList getSIXSUMHistoryList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange("https://1680660.com/smallSixMobile/findSmallSixHistory.do?year=2019", HttpMethod.GET, entity, JSONObject.class);
        JSONObject body = response.getBody().getJSONObject("result");
        JSONArray dataArray = body.getJSONArray("data");
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        for (Object ob :
                dataArray) {
            JSONObject jsonObject = (JSONObject) ob;
            arrayList.add(lotteryUtils.SIXSUM_utils(jsonObject));
        }
        return arrayList;
    }


    @RequestMapping(value = "/lottery/newTicketInfo/{lotCode}", method = RequestMethod.GET)
    public HashMap<String, Object> getNewTickerInfo(@PathVariable("lotCode") String lotCode) {
        Map map = new HashMap();
        if (lotCode.equals("11009")) {
            JSONObject jsonObject = this.getSIXSUMHistory();
            map = JSONObject.toJavaObject(jsonObject, Map.class);
            return ResultGen.getResult((Map<Object, Object>) map, 0);
        }
        if (lotteryUtils.CodeVerification(lotCode)) {
            //参数错误
            return ResultGen.getResult((HashMap<Object, Object>) map, 4);
        } else {
            if (service.findCode(lotCode) == 0) {
                //彩种不存在
                return ResultGen.getResult((HashMap<Object, Object>) map, 2);
            } else {
                ExLotteryTicket t = service_ex.getNewTicketInfo(lotCode);
                if (t == null) {
                    //期数不存在
                    return ResultGen.getResult((HashMap<Object, Object>) map, 3);
                } else {
                    Long issue = service.getFirstIssue(lotCode);
                    if (issue != null) {
                        map = ticketGen.getresult(t, t.getLot_type(),service_ex,service);
                    } else {
                        map = ticketGen.getresult(t, t.getLot_type(),service_ex,service);
                    }
                    return ResultGen.getResult((HashMap<Object, Object>) map, 0);
                }
            }
        }
    }

    @RequestMapping(value = "/lottery/indexTicketsSort", method = RequestMethod.GET)
    public String[] sort() {
        //return new String[]{"11009", "10057", "10002", "10001", "11006", "11008", "11003", "11010", "11007", "11002", "110001", "11005", "11004"};
        return new String[]{"11009","10057","10002","10001","11006","11008","11003","11010","11013","11007","11002","11001","11012","11005","11004"};
    }

    private List<String> String2ArrayList(String[] strings) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str :
                strings) {
            arrayList.add(str);
        }
        return arrayList;
    }

    @RequestMapping(value = "/lottery/indexTickets", method = RequestMethod.GET)
    public HashMap<String, Object> getIndexTickets() {
        long timestamp = System.currentTimeMillis();
        ArrayList arr = new ArrayList();
        this.getSIXSUMHistory();
        arr.add(this.getSIXSUMHistory());
        List<String> str = this.String2ArrayList(this.sort());
        str.remove(0);//香港
        ArrayList<ExLotteryTicket> ex = (ArrayList<ExLotteryTicket>) service_ex.getIndexTickets(str);
        Map map = new HashMap();
        for (ExLotteryTicket e :
                ex) {
            Long issue = service.getFirstIssue(e.getLot_code());
            if (issue == null) {
                map = ticketGen.getresult(e, e.getLot_type(),service_ex,service);
                arr.add(map);
            } else {
                map = ticketGen.getresult(e, e.getLot_type(),service_ex,service);
                arr.add(map);
            }
        }
        System.out.println(System.currentTimeMillis() - timestamp);
        return ResultGen.getResult(arr, 0);


    }


    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/LongCount", method = RequestMethod.GET)
    public Object longDragonRemindByLotCode(@PathVariable("lotCode") String lotCode) {
        return service_ex.getLongDragonInfo(lotCode);
    }

//    private void countFun(Map map, String lotCode) {
//        int count = Integer.parseInt(service.getCurrentCount(lotCode));
//        map.put("currentCount", count);
//        int totalCount = (int) map.get("totalCount");
//        map.put("surplusCount", totalCount - count);
//    }

    @RequestMapping(value = "/lottery/ticketTypeInfo", method = RequestMethod.GET)
    public Object getTicketTypeInfo() {
        return service_ex.getTicketTypeInfo();
    }


    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/EvenCount", method = RequestMethod.GET)
    public Object getDoubleNumber(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketList(lotCode);
        if (list != null) {
            int type = list.get(0).getLot_type();
            JSONArray jsonArray = new JSONArray();
            if (type == 1 || type == 4 || type == 6) {
                jsonArray.add(lotteryUtils.doubleNumberCount(list));
                return jsonArray;
            }
            if (type == 2 || type == 3) {

                jsonArray.add(lotteryUtils.doubleNumberCount(list));
                jsonArray.add(lotteryUtils.allNumberCount(list));
                return jsonArray;
            }
        }
        return null;
    }


    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/SizeParityHistory", method = RequestMethod.GET)
    public JSONObject getSizeParityHistory(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketInfoDoubleList(lotCode);
        if (list != null) {
            JSONObject js = new JSONObject();
            js.put("complexView", lotteryUtils.complexView(list));
            js.put("singleView", lotteryUtils.singleView(list));
            return js;
        }
        return null;
    }

    //单双大小路珠
    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/SizeParityLine", method = RequestMethod.GET)
    public JSONObject SizeParityLine(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketList(lotCode);
        if (list != null) {
            JSONObject js = lotteryUtils.getDSBSRoadBead(list);
            return js;
        }
        return null;
    }

    //龙虎路珠
    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/DragonTigerLine", method = RequestMethod.GET)
    public JSONObject DragonTigerLine(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketList(lotCode);
        if (list != null) {
            JSONObject js = lotteryUtils.getDTRoadBead(list);
            return js;
        }
        return null;
    }

    //冠亚和路珠
    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/QuinellaLine", method = RequestMethod.GET)
    public JSONObject FS_Line(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketList(lotCode);
        if (list != null) {
            JSONObject js = lotteryUtils.getFSRoadBead(list);
            return js;
        }
        return null;
    }

    //总和路珠（ssc）
    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/TotalLine", method = RequestMethod.GET)
    public JSONObject TotalLine(@PathVariable("lotCode") String lotCode) {
        ArrayList<ExLotteryTicket> list = (ArrayList<ExLotteryTicket>) service_ex.getTicketList(lotCode);
        if (list != null) {
            JSONObject js = lotteryUtils.getTotalRoadBead(list);
            return js;
        }
        return null;
    }


    @RequestMapping(value = "/lottery/allTickets", method = RequestMethod.GET)
    public net.sf.json.JSONArray getAllTicket() {
        return net.sf.json.JSONArray.fromObject("[{\"name\":\"热门彩\",\"list\":[{\"lotCode\":\"11009\",\"lotType\":\"7\",\"lotName\":\"香港六合彩\"},{\"lotCode\":\"10057\",\"lotType\":\"1\",\"lotName\":\"幸运飞艇\"},{\"lotCode\":\"10002\",\"lotType\":\"2\",\"lotName\":\"重庆欢乐生肖\"},{\"lotCode\":\"10001\",\"lotType\":\"1\",\"lotName\":\"北京PK10\"},{\"lotCode\":\"11006\",\"lotType\":\"5\",\"lotName\":\"台北快三\"},{\"lotCode\":\"11008\",\"lotType\":\"1\",\"lotName\":\"香港跑马\"},{\"lotCode\":\"11003\",\"lotType\":\"2\",\"lotName\":\"QQ分分彩\"},{\"lotCode\":\"11010\",\"lotType\":\"1\",\"lotName\":\"极速赛车\"},{\"lotCode\":\"11013\",\"lotType\":\"7\",\"lotName\":\"五分六合彩\"},{\"lotCode\":\"11007\",\"lotType\":\"1\",\"lotName\":\"五分赛车\"},{\"lotCode\":\"11002\",\"lotType\":\"2\",\"lotName\":\"五分时时彩\"},{\"lotCode\":\"11001\",\"lotType\":\"2\",\"lotName\":\"三分时时彩\"},{\"lotCode\":\"11012\",\"lotType\":\"7\",\"lotName\":\"十分六合彩\"},{\"lotCode\":\"11005\",\"lotType\":\"5\",\"lotName\":\"三分快三\"},{\"lotCode\":\"11004\",\"lotType\":\"5\",\"lotName\":\"五分快三\"}]},{\"name\":\"PK10系列\",\"list\":[{\"lotCode\":\"11007\",\"lotType\":\"1\",\"lotName\":\"五分赛车\"},{\"lotCode\":\"10001\",\"lotType\":\"1\",\"lotName\":\"北京PK10\"},{\"lotCode\":\"11010\",\"lotType\":\"1\",\"lotName\":\"极速赛车\"},{\"lotCode\":\"11008\",\"lotType\":\"1\",\"lotName\":\"香港跑马\"},{\"lotCode\":\"10057\",\"lotType\":\"1\",\"lotName\":\"幸运飞艇\"}]},{\"name\":\"时时彩系列\",\"list\":[{\"lotCode\":\"11002\",\"lotType\":\"2\",\"lotName\":\"五分时时彩\"},{\"lotCode\":\"11001\",\"lotType\":\"2\",\"lotName\":\"三分时时彩\"},{\"lotCode\":\"11003\",\"lotType\":\"2\",\"lotName\":\"QQ分分彩\"},{\"lotCode\":\"10003\",\"lotType\":\"2\",\"lotName\":\"天津时时彩\"},{\"lotCode\":\"10004\",\"lotType\":\"2\",\"lotName\":\"新疆时时彩\"}]},{\"name\":\"快三系列\",\"list\":[{\"lotCode\":\"10007\",\"lotType\":\"5\",\"lotName\":\"江苏快3\"},{\"lotCode\":\"10026\",\"lotType\":\"5\",\"lotName\":\"广西快3\"},{\"lotCode\":\"10027\",\"lotType\":\"5\",\"lotName\":\"吉林快3\"},{\"lotCode\":\"10028\",\"lotType\":\"5\",\"lotName\":\"河北快3\"},{\"lotCode\":\"10029\",\"lotType\":\"5\",\"lotName\":\"内蒙古快3\"},{\"lotCode\":\"10030\",\"lotType\":\"5\",\"lotName\":\"安徽快3\"},{\"lotCode\":\"10031\",\"lotType\":\"5\",\"lotName\":\"福建快3\"},{\"lotCode\":\"10032\",\"lotType\":\"5\",\"lotName\":\"湖北快3\"},{\"lotCode\":\"10033\",\"lotType\":\"5\",\"lotName\":\"北京快3\"},{\"lotCode\":\"11006\",\"lotType\":\"5\",\"lotName\":\"台北快3\"},{\"lotCode\":\"11004\",\"lotType\":\"5\",\"lotName\":\"五分快3\"},{\"lotCode\":\"11005\",\"lotType\":\"5\",\"lotName\":\"三分快3\"}]},{\"name\":\"11选5系列\",\"list\":[{\"lotCode\":\"11011\",\"lotType\":\"3\",\"lotName\":\"东京11选5\"},{\"lotCode\":\"10006\",\"lotType\":\"3\",\"lotName\":\"广东11选5\"},{\"lotCode\":\"10017\",\"lotType\":\"3\",\"lotName\":\"安徽11选5\"},{\"lotCode\":\"11014\",\"lotType\":\"3\",\"lotName\":\"北京11选5\"},{\"lotCode\":\"11015\",\"lotType\":\"3\",\"lotName\":\"湖北11选5\"},{\"lotCode\":\"10016\",\"lotType\":\"3\",\"lotName\":\"江苏11选5\"},{\"lotCode\":\"10019\",\"lotType\":\"3\",\"lotName\":\"辽宁11选5\"},{\"lotCode\":\"10018\",\"lotType\":\"3\",\"lotName\":\"上海11选5\"}]},{\"name\":\"快乐十分系列\",\"list\":[{\"lotCode\":\"10005\",\"lotType\":\"4\",\"lotName\":\"广东快乐十分\"},{\"lotCode\":\"10046\",\"lotType\":\"8\",\"lotName\":\"PC蛋蛋幸运28\"},{\"lotCode\":\"10011\",\"lotType\":\"4\",\"lotName\":\"澳洲幸运8\"},{\"lotCode\":\"10034\",\"lotType\":\"4\",\"lotName\":\"天津快乐十分\"},{\"lotCode\":\"10009\",\"lotType\":\"6\",\"lotName\":\"重庆幸运农场\"}]},{\"name\":\"六合彩系列\",\"list\":[{\"lotCode\":\"11009\",\"lotType\":\"7\",\"lotName\":\"香港六合彩\"},{\"lotCode\":\"11012\",\"lotType\":\"7\",\"lotName\":\"十分六合彩\"},{\"lotCode\":\"11013\",\"lotType\":\"7\",\"lotName\":\"五分六合彩\"}]}]");
    }


    public enum saveSpace {
        INSTANCE;

        private JSONObject value;

        private saveSpace() {
            value = new JSONObject();
        }

        public JSONObject getValue() {
            return value;
        }

        public void setValue(JSONObject value) {
            this.value = value;
        }

        public void setValue(String key, Object value) {
            if (this.value.get(key) != null) {
                this.value.remove(key);
                this.value.put(key, value);
                System.out.println(key + " update " + value);
            } else {
                this.value.put(key, value);
                System.out.println(key + " create " + value);
            }
        }
    }

    public enum firstIssue {
        INSTANCE;

        private JSONObject value;

        private firstIssue() {
            value = new JSONObject();
        }

        public JSONObject getValue() {
            return value;
        }

        public void setValue(JSONObject value) {
            this.value = value;
        }

        public void setValue(String key, Object value) {
            if (this.value.get(key) != null) {
                this.value.remove(key);
                this.value.put(key, value);
                System.out.println(key + " update " + value);
            } else {
                this.value.put(key, value);
                System.out.println(key + " create " + value);
            }
        }
    }


}
