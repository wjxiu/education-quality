package com.github.wjxiu.controller;


import com.github.wjxiu.DTO.Req.LoginReq;
import com.github.wjxiu.common.Exception.AbstractException;
import com.github.wjxiu.common.R;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiu
 * @create 2024-01-25 12:32
 */
@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Test
    public void login() {
        assertThrows(AbstractException.class, () -> {
            LoginReq loginReq = new LoginReq();
            loginReq.setId(1);
            loginReq.setType(4);
            loginReq.setPassword("123");
            R login = userController.login(loginReq);

        });
        assertDoesNotThrow(() -> {
            LoginReq loginReq = new LoginReq();
            loginReq.setId(1);
            loginReq.setType(0);
            loginReq.setPassword("123");
            R login = userController.login(loginReq);
            System.out.println(login);
        });
        assertThrows(AbstractException.class, () -> {
            LoginReq loginReq = new LoginReq();
            loginReq.setId(1);
            loginReq.setType(0);
            loginReq.setPassword("1234233423");
            R login = userController.login(loginReq);
        });


    }

    @Test
    public void register() {
    }

    @Test
    public void changepasswd() {
    }

    @Test
    public void userInfo() {
        assertThrows(AbstractException.class, () -> {
                    userController.userInfo("sd");
                }
        );
        assertDoesNotThrow(() -> {
                    userController.userInfo("Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MDYxNTkzNzksImlzcyI6ImVkdSIsInN1YiI6IntcInJlYWxOYW1lXCI6XCLkuZTlrZDnkb5cIixcInR5cGVcIjowLFwidXNlcklkXCI6MX0iLCJleHAiOjE3Mzc2OTUzODB9.S7iLRPr2LQbPQUh7TCwDY4XzlhSxE0wX3iulL9HM-AEdlS6eyuSePKPnmSZhPbKnxeJsw6irK9Kq_Try9n-QIg");
                }
        );
    }

    @Test
    public void logout() {
    }
}