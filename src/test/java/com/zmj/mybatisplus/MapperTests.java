package com.zmj.mybatisplus;

import com.zmj.mybatisplus.entity.User;
import com.zmj.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTests {
    //@Autowired //默认按类型装配。是spring的注解
    @Resource //默认按名称装配，找不到与名称匹配的bean，则按照类型装配。是J2EE的注解
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Helen");
        user.setAge(18);
        //不设置email属性，则生成的动态sql中不包括email字段
        int result = userMapper.insert(user);
        System.out.println("影响的行数：" + result); //影响的行数
        System.out.println("id：" + user.getId()); //id自动回填
    }

    @Test
    void testSelectList() {
        //SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        //SELECT id,name,age,email FROM user WHERE id = 1
        User user = userMapper.selectById(1);
        System.out.println(user);

        //SELECT id,name,age,email FROM user WHERE id IN ( 1 , 2 , 3)
        users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","Helen"); //注意此处是表中的列名，不是类中的属性名
        paramMap.put("age",18);
        users = userMapper.selectByMap(paramMap);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setAge(28);

        //注意：update时生成的sql自动是动态sql,根据上面的赋值得到
        //UPDATE user SET age=28 WHERE id=1
        int result = userMapper.updateById(user);
        System.out.println("影响的行数：" + result);
    }

    @Test
    public void testDelete(){
        //DELETE FROM user WHERE id=5
        int result = userMapper.deleteById(5);
        System.out.println("影响的行数：" + result);
    }


}
