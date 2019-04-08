package com.hash.by_lottery.Service.Impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.dao.BaseLotteryTicketDao;
import com.hash.by_lottery.dao.ExLotteryTicketDao;
import com.hash.by_lottery.entities.ExLotteryTicket;
import com.hash.by_lottery.entities.LongDragon;
import com.hash.by_lottery.utils.longDragonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ExLotteryTickerServiceImpl
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 14:30
 * @Version 1.0
 **/

@Service
public class ExLotteryTicketServiceImpl implements ExLotteryTicketService {


    @Autowired
    private ExLotteryTicketDao dao;

    @Autowired
    private BaseLotteryTicketDao dao_;


    @Override
    public List<ExLotteryTicket> stuffTickerInfo(String code) {

        return dao.stuffInfoToExLotteryTicket(code);
    }

    public ExLotteryTicket getNewTicketInfo(String code) {
        return dao.getNewTicketInfo(code);
    }

    @Override
    public List<ExLotteryTicket> getTicketList(String code) {

        return dao.getTicketInfoList(code) != null ? dao.getTicketInfoList(code) : dao.getTicketInfoList_(code);
    }

    @Override
    public List<ExLotteryTicket> getTicketListWithTime(String code, String time) {
        return dao.getTicketInfoListWithTime(code, time);
    }

    @Override
    public List<JSONObject> getLimitTicketList(String code, int limit) {
        List<ExLotteryTicket> list = new ArrayList<>();
        List<String> list_issue = new ArrayList<>();
        List<JSONObject> list_code = new ArrayList<>();
        limit = (Integer.parseInt(dao_.getCurrentCount(code)) < 25 ? Integer.parseInt(dao_.getCurrentCount(code)) : 25);
        Stack<ExLotteryTicket> stack = new Stack<>();
        for (ExLotteryTicket e :
                dao.getLimitTicketList(code, limit)) {
            stack.push(e);
        }

        for (int i = 0; i < limit; i++) {
            list.add(i, stack.pop());
        }

        //获取期数列表
        for (ExLotteryTicket t : list
        ) {
            list_issue.add(t.getDraw_issue());
        }
        ExLotteryTicket t = list.get(0);
        int length = t.getDraw_code().split(",").length;
        int index = limit;
        for (int i = 0; i < length; i++) {
            String[] str = new String[index];
            for (int j = 0; j < index; j++) {
                str[j] = list.get(j).getDraw_code().split(",")[i];
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("issue", list_issue);
            jsonObject.put("code", str);
            list_code.add(jsonObject);
        }
        HashMap map = new HashMap();
        JSONArray array = new JSONArray();
        return list_code;
    }


    public List getLongDragonInfo(String lot_code) {
        boolean flag = false;
        ExLotteryTicket t = dao.getNewTicketInfo(lot_code);
        Long issue = Long.parseLong(t.getDraw_issue()) - 1;
        ExLotteryTicket t_ = dao.getTicketInfoByIssue(lot_code, String.valueOf(issue));
        List CRAS = longDragonUtils.countRankAndState(t.getLot_type(), t, t_);
        List<LongDragon> PM = longDragonUtils.positionMarker(CRAS);
        while (true) {
            if (Long.parseLong(t_.getDraw_issue())%100==1){
                break;
            }
            t = t_;
            issue = Long.parseLong(t.getDraw_issue()) - 1;
            t_ = dao.getTicketInfoByIssue(lot_code, String.valueOf(issue));
            CRAS = longDragonUtils.countRankAndState(t.getLot_type(), t, t_);
            flag = longDragonUtils.counter(PM, CRAS);
            if (flag == false) {
                break;
            }
        }
        for (LongDragon ld :
                PM) {
            ld.setRank(ld.getRank() + 1);
            ld.setState(ld.getState() + 1);
        }
        return PM;
    }

    @Override
    public JSONObject getTicketTypeInfo() {
        ArrayList<ExLotteryTicket> arr = (ArrayList) dao.getTicketTypeInfo();
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        if (arr!=null){
            for (ExLotteryTicket e:
                 arr) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("lotCode",e.getLot_code());
                jsonObject.put("lotName",e.getLot_name());
                jsonObject.put("lotType",e.getLot_type());
                jsonObject.put("iconUrl",e.getLot_imgUrl());
                jsonObject.put("lotState",e.getLot_state());
                jsonObject.put("isHot",e.getIs_hot());
                array.add(jsonObject);
            }
            object.put("error_code",0);
            object.put("data",array);
        }else {
            object.put("error_code",1);
            object.put("data","数据出错");
        }
        return object;
    }


    public List<ExLotteryTicket> getTicketInfoDoubleList(String code){
        return dao.getTicketInfoDoubleList(code);
    }



}