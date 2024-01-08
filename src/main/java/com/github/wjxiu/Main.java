package com.github.wjxiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiu
 * @create 2024-01-06 20:30
 */
@MapperScan(basePackages = "com.github.wjxiu.mapper")
@SpringBootApplication(scanBasePackages = "com.github.wjxiu")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}