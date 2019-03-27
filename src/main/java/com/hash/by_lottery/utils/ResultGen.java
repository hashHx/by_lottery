package com.hash.by_lottery.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultGen
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/23 18:56
 * @Version 1.0
 **/

public class ResultGen {



    public enum error {
        NOT_CONNECTION("服务器出错或数据库连接失败!", 1), LOT_NON_EXISTENT("彩种不存在!", 2), ISSUE_NON_EXISTENT("期数不存在!", 3), ILLEGAL_PARAMETER("非法参数!", 4);

        private String msg;
        private int code;

        private error(String msg, int code) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static HashMap<String, Object> getResult(Object data, int state) {
        Map map = new HashMap<>();
        switch (state) {
            case 2:
                map.put("error_code", 2);
                map.put("msg", error.LOT_NON_EXISTENT.getMsg());
                map.put("data", "");
                break;
            case 3:
                map.put("error_code", 3);
                map.put("msg", error.ISSUE_NON_EXISTENT.getMsg());
                map.put("data", "");
                break;
            case 4:
                map.put("error_code", 3);
                map.put("msg", error.ILLEGAL_PARAMETER.getMsg());
                map.put("data", "");
                break;
            case 0:
                map.put("error_code", 0);
                map.put("msg", "查询成功！");
                map.put("data", data);
                break;
            default:
                map.put("error_code", 0);
                map.put("msg", error.NOT_CONNECTION.getMsg());
                map.put("data", "");
                break;

        }
        return (HashMap<String, Object>) map;
    }


}
