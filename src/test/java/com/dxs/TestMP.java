package com.dxs;

import com.dxs.dao.UserMapper;
import com.dxs.pojo.Users;
import com.dxs.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/*.xml")
public class TestMP {
    @Autowired
    private UserService userService;

    //final Logger logger = LoggerFactory.getLogger(TestMP.class);
    @Test
    public void testSelect(){
        //logger.debug("debug：....");
        Users user = userService.selectById(6);
        System.out.println(user);
    }
}
