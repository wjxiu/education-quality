package com.github.wjxiu;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiu
 * @create 2024-01-06 20:30
 */
@EnableTransactionManagement
@MapperScan(basePackages = "com.github.wjxiu.mapper")
@SpringBootApplication(scanBasePackages = "com.github.wjxiu")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}