package com.github.wjxiu.common.Exception.ExceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这里处理Controller前抛出的异常
 * @author xiu
 * @create 2023-12-10 13:40
 */
@RestController
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        log.info("--------------------------------");
        log.info("--------------------------------");
        log.info("--------------------------------");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        // 获取错误状态码
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        log.info(statusCode+"-=--------------------------------");
        Enumeration<String> attributeNames = request.getAttributeNames();
        Iterator<String> iterator = attributeNames.asIterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            String string = iterator.next().toString();
            builder.append(string).append("::::").append(request.getAttribute(string)).append("\n");
        }
        if (errorMessage==null|| errorMessage.isEmpty())return ResponseEntity.status(statusCode != null ? statusCode : 999).body(builder.toString());
        // 在这里处理自定义的错误逻辑，并返回JSON格式的错误信息
//        String errorMessage = throwable.getMessage();
//        String errorMessageReal = extractErrorCode(errorMessage);
        return ResponseEntity.status(statusCode != null ? statusCode : 999).body("errorCode:" + errorMessage + ";");
    }

    private static String extractErrorMessage(String input) {
        String regex = "errorMessage=(.*?)(\\)|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Error message not found";
        }
    }

    private static String extractErrorCode(String input) {
        String regex = "errorCode=(.*?)(\\)|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Error Code not found";
        }
    }

    public String getErrorPath() {
        return "/error";
    }
}


