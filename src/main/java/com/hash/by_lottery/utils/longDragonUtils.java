package com.hash.by_lottery.utils;

/**
 * @ClassName longDragonUtils
 * @Descritption 长龙玩法工具类
 * @Author Hash
 * @Date 2019/3/27 20:37
 * @Version 1.0
 **/

public class longDragonUtils {

    private volatile static longDragonUtils instance = null;

    private longDragonUtils(){};

    public static longDragonUtils getInstance(){

        if (instance==null){
            synchronized (longDragonUtils.class){
                if (instance==null){
                    instance = new longDragonUtils();
                }
            }
        }

        return instance;
    }




}

