package com.hash.by_lottery.controller;

import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName lotterTickerController
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:33
 * @Version 1.0
 **/

@RestController
public class LotterTicketController {

    @Autowired
    private BaseLotteryTicketService service;

    @Autowired
    private ExLotteryTicketService service_ex;

    @RequestMapping(value = "/lottery/ticketInfo/{lotCode}/{lotIssue}",method = RequestMethod.GET)
    public String getTickerInfo(@PathVariable("lotCode") String lotCode, @PathVariable("lotIssue") String lotIssue){

        return service.getTickerInfo(lotCode,lotIssue).toString();
    }


    @RequestMapping(value="/lottery/spider",method = RequestMethod.POST)
    public String getSpiderInfo(BaseLotteryTicket ticket){
        if (ticket!=null){
        ticket.setL_id(IdGen.get().nextId());
        return String.valueOf(service.saveBaseTicketInfo(ticket));}
        else {
            return "error";
        }
    }

    @RequestMapping(value = "/lottery/ExTicketInfo/{lotCode}",method = RequestMethod.GET)
    public List<ExLotteryTicket> getExTicketInfo(@PathVariable("lotCode") String lotCode){
        return service_ex.stuffTickerInfo(lotCode);
    }

    @RequestMapping(value = "/lottery/ExTicket/{lotCode}/{lotIssue}",method = RequestMethod.GET)
    public ExLotteryTicket getExTicker(@PathVariable("lotCode") String lotCode, @PathVariable("lotIssue") String lotIssue){

        return service_ex.getNewTicketInfo(lotCode, lotIssue);
    }

    @RequestMapping(value = "lottery/InterfaceInfo",method = RequestMethod.POST)
    public String getInfoFromInterface(String data) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
//        BaseLotteryTicket b = lotteryDecode.getBaseFromStore("eyJpdiI6IkJyZGRzS0ZFMUlLMmUwTzkrZ1wvSnNnPT0iLCJ2YWx1ZSI6IjJWK2pERGJUZ3FDU0JZajJha1B4djJFQmdVQXZtQ1k2RWVKdTVic21JRFp0Y2ZGakJuRUhaY21aY0tGdU9YOUsremNQS1RQZ2tJVENVSTN1MzFyNGRQWUhLQ2thTXJ0MlhwRzhSVVZtVUxHb0lZM2k2aTJNa1VCalNlV0N1dlhjQXZoOE5HYjNIQVVMMU5BZ3lxMVZrSDFKQ3lNTk1sdUxSSVVZU1dWVW5tVHRJRXdvd0R0XC9oclZsRlwvQXNtZ3FHdEhqd3FSXC9GRm4za0ZraWpBd0R4VGRxcDh6MFg4K3lHWGYxNEtBMU55Z3VxcnpOVTQrRElwRE9wSDZ1NGtMMzRLNlJDZnc1UnlqSXhCdFIxNUJkNXd1ZWRqWVpkTHRoc2JSRWlDYmF0b0ZNUkR4QzdiV2pIaXFodVEwbUh1M1wvcXJwd0tMZHVzRHROdFdnY2N6VStQZTFMckgyeXQrQ2RhelE5bVdpWFkrSlhPb3ZlemR0Znd6dExua1JXOHdjTHYiLCJtYWMiOiI1MDllNjQxZWI5NGNjZGEyOWRjOGQ4NzNlMzJlMzM4MzZhYWNjNzI2NGFmMjBkYTIwNjg3YWJiOTA2YmQ5Njk2In0=");
        BaseLotteryTicket b = lotteryDecode.getBaseFromStore(data);
        if (b!=null){
            service.saveBaseTicketInfo(b);
            return "ok";
        }
        return "error";
    }


    @RequestMapping(value = "/lottery/TicketInfo/{lotCode}/{lotIssue}",method = RequestMethod.GET)
    public HashMap<String, Object> getExTickerInfo(@PathVariable("lotCode") String lotCode, @PathVariable("lotIssue") String lotIssue){

        Map map = new HashMap();
        try {
            if (lotteryUtils.CodeVerification(lotCode, lotIssue)){
                //参数错误
                return ResultGen.getResult((HashMap<Object, Object>) map,4);
            }else {
                if (service.findCode(lotCode)==0){
                    //彩种不存在
                    return ResultGen.getResult((HashMap<Object, Object>) map,2);
                }else{
                    ExLotteryTicket t = getExTicker(lotCode, lotIssue);
                    if (t==null){
                        //期数不存在
                        return ResultGen.getResult((HashMap<Object, Object>) map,3);
                    }else {
                        map = ticketGen.getresult(t,t.getLot_type());
                        return ResultGen.getResult((HashMap<Object, Object>) map,0);
                    }
                }
            }
        }catch (Exception e){
            return ResultGen.getResult((HashMap<Object, Object>) map,1);
        }

    }


    private void countFun(Map map,String lotCode){
        int count = Integer.parseInt(service.getCurrentCount(lotCode));
        map.put("currentCount",count);
        int totalCount = (int)map.get("totalCount");
        map.put("surplusCount",totalCount-count);

    }
}
