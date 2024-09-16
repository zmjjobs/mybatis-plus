package com.zmj.mybatisplus;

import com.zmj.mybatisplus.entity.User;
import com.zmj.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTests {
    @Resource
    private UserService userService;

    @Test
    public void testCount(){
        //SELECT COUNT(*) FROM user
        int count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testSaveBatch(){
        // SQL长度有限制，海量数据插入单条SQL无法实行，
        // 因此mybatis-plus将批量插入放在了通用Service中实现，而不是通用Mapper
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setName("Helen" + i);
            user.setAge(10 + i);
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    public void listByName(){
        List<User> users = userService.listByName("Ja");
        users.forEach(System.out::println);
    }
}
