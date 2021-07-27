package com.shu;

import com.shu.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {


    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {

    }

}
