package com.hash.by_lottery.utils;


import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.entities.BaseLotteryTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;


/**
 * @Description: 彩票解密工具类
 * @Author: Hash
 * @Date: 2019/3/23
 */
public class lotteryDecode {

    Logger logger = LoggerFactory.getLogger(lotteryDecode.class);

    //    *绑定彩票缩写
    public enum lot_abbreviation {
        CQSSC("10002"), XJSSC("10004"), TJSSC("10003"), GD11Y("10006"), JSK3("10007"),
        BJPK10("10001"), XYFT("10057");

        private String code;

        lot_abbreviation(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }


    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public static String decodeResult(String Data, String key) throws UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] asBytes = Base64.getDecoder().decode(Data);
        String disc = new String(asBytes, "utf-8");
        String[] discdata = disc.split("\"");
        String iv = discdata[3].replace("\\/", "/");
        String value = discdata[7].replace("\\/", "/");
        byte[] iv_ = Base64.getDecoder().decode(iv);
        byte[] value_ = Base64.getDecoder().decode(value);
        return new String(AES_cbc_decrypt(value_, key.getBytes(), iv_));
    }

    public static byte[] AES_cbc_decrypt(byte[] encData, byte[] key, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] decbbdt = cipher.doFinal(encData);
        return decbbdt;
    }


    public static BaseLotteryTicket getBaseFromStore(String data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String key = "07edde1ca4efc5fbb5577053fa621926";
        String a = decodeResult(data, key);
        String b[] = a.split(":", 3);
        String c = b[2].substring(1, b[2].length() - 2);
        Map map = (Map) JSONObject.parse(c);
        JSONObject jsonObjectData = (JSONObject) map.get("data");
        String time = (String) map.get("time");
        String s = jsonObjectData.getString("lottery");
        return new BaseLotteryTicket().setL_id(IdGen.get().nextId()).setDraw_code(jsonObjectData.getString("code")).setDraw_issue(jsonObjectData.getString("issue")).setLot_code(lot_abbreviation.valueOf(jsonObjectData.getString("lottery")).getCode()).setDraw_time(time);
    }

}





