package com.hash.by_lottery.controller;


import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.entities.BaseLotteryTicket;
import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @ClassName lotterTickerController
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:33
 * @Version 1.0
 **/

@RestController
public class LotterTicketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

        return service_ex.getNewTicketInfo(lotCode);
    }



    @RequestMapping(value = "lottery/InterfaceInfo",method = RequestMethod.POST)
    public String getInfoFromInterface(HttpServletRequest request, String data) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Map<String,String[]> requestMsg = request.getParameterMap();

        for(String key :requestMsg.keySet())
        {
            for(int i=0;i<requestMsg.get(key).length;i++)
            {
                logger.trace(key);
                BaseLotteryTicket b = lotteryDecode.getBaseFromStore(key);
                logger.trace(b.toString());
                if (b!=null){
                    service.saveBaseTicketInfoFromPush(b);
                    return "ok";
                }
            }
        }


        return "error";
    }


    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}",method = RequestMethod.GET)
    public Object getExTickerInfo(@PathVariable("lotCode") String lotCode){

        Map map = new HashMap();
            if (lotteryUtils.CodeVerification(lotCode)){
                //参数错误
                return ResultGen.getResult((HashMap<Object, Object>) map,4);
            }else {
                if (service.findCode(lotCode)==0){
                    //彩种不存在
                    return ResultGen.getResult((HashMap<Object, Object>) map,2);
                }else{
                    List<ExLotteryTicket> list = service_ex.getTicketList(lotCode);
                    List<Object> ticketList = new ArrayList();
                    for (ExLotteryTicket e : list
                         ) {
                        map = ticketGen.getresult(e,e.getLot_type());
                        ticketList.add(map);
                    }
                        return ResultGen.getResult(ticketList,0);
                }
            }
    }
    @RequestMapping(value = "/lottery/TicketInfoList/{lotCode}/{time}",method = RequestMethod.GET)
    public Object getExTickerInfoWithTime(@PathVariable("lotCode") String lotCode,@PathVariable("time") String time){

        Map map = new HashMap();
        if (lotteryUtils.CodeVerification(lotCode)){
            //参数错误
            return ResultGen.getResult((HashMap<Object, Object>) map,4);
        }else {
            if (service.findCode(lotCode)==0){
                //彩种不存在
                return ResultGen.getResult((HashMap<Object, Object>) map,2);
            }else{
                List<ExLotteryTicket> list = service_ex.getTicketListWithTime(lotCode,time);
                List<Object> ticketList = new ArrayList();
                for (ExLotteryTicket e : list
                ) {
                    map = ticketGen.getresult(e,e.getLot_type());
                    ticketList.add(map);
                }
                return ResultGen.getResult(ticketList,0);
            }
        }
    }


    @RequestMapping(value = "/lottery/newTicketInfo/{lotCode}",method = RequestMethod.GET)
    public HashMap<String, Object> getNewTickerInfo(@PathVariable("lotCode") String lotCode){

        Map map = new HashMap();
       // try {
            if (lotteryUtils.CodeVerification(lotCode)){
                //参数错误
                return ResultGen.getResult((HashMap<Object, Object>) map,4);
            }else {
                if (service.findCode(lotCode)==0){
                    //彩种不存在
                    return ResultGen.getResult((HashMap<Object, Object>) map,2);
                }else{
                    ExLotteryTicket t = service_ex.getNewTicketInfo(lotCode);
                    if (t==null){
                        //期数不存在
                        return ResultGen.getResult((HashMap<Object, Object>) map,3);
                    }else {
                        map = ticketGen.getresult(t,t.getLot_type());
                        return ResultGen.getResult((HashMap<Object, Object>) map,0);
                    }
                }
            }
       // }catch (Exception e){
          //  return ResultGen.getResult((HashMap<Object, Object>) map,1);
       // }

    }


    private void countFun(Map map,String lotCode){
        int count = Integer.parseInt(service.getCurrentCount(lotCode));
        map.put("currentCount",count);
        int totalCount = (int)map.get("totalCount");
        map.put("surplusCount",totalCount-count);

    }
}
