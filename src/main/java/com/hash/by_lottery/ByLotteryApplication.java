package com.hash.by_lottery;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ByLotteryApplication {
    private static final Logger logger = Logger.getLogger(ByLotteryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ByLotteryApplication.class, args);
    }

}
