package com.hash.by_lottery.utils;

/**
 * @ClassName lotteryCodeAdapter
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/21 15:58
 * @Version 1.0
 **/

public class lotteryCodeAdapter {

    public static String toDbString(String code){
        //包含空格的格式
        if (code.contains(" ")){
            return code.replace(" ",",");
        }
        //包含 , 的格式
        if(code.contains(",")){
            return code;
        }
        //自然数格式
        else {
            String[] codes = new String[code.length()];
            for (int i = 0; i < code.length() ; i++) {
                codes[i] = code.substring(i,i+1);
            }
            String result = codes[0]+",";
            for (int i = 1; i < code.length(); i++) {
                result = result + codes[i] + ",";
            }
            return result.substring(0,result.length()-1);
        }


    }

    public static int[] toCalculate(String code){

        String[] sResult = code.split(",");
        int[] result = new int[sResult.length];
        for (int i = 0; i < sResult.length ; i++) {
            result[i]  = Integer.parseInt(sResult[i]);
        }

        return result;
    }


}
