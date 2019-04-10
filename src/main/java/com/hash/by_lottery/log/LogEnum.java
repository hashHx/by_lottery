package com.hash.by_lottery.log;

/**
 * @ClassName LogEnum
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/4/10 13:01
 * @Version 1.0
 **/

public enum  LogEnum {


    BUSSINESS("bussiness"),

    PLATFORM("platform"),

    DB("db"),

    EXCEPTION("exception"),

    ;


    private String category;


    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
