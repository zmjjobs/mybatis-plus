package com.zmj.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zmj.mybatisplus.entity.User;
import com.zmj.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTests {
    
    @Resource
    private UserMapper userMapper;

    @Test
    public void test1() {
        String likeName = "n";
        Integer ageFrom = 10;
        Integer ageTo = 20;
        boolean hasEmail = true;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(likeName),"name",likeName)
                //.between("age", 10, 20) 如果确定两个值不为空
                .le(ageTo != null,"age",ageTo)
                .ge(ageFrom != null,"age",ageFrom)
                .isNotNull(hasEmail,"email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper); //条件构造器也可以构建删除语句的条件
        System.out.println("delete return count = " + result);
    }

    @Test
    public void test4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda表达式内的逻辑优先运算
        queryWrapper.like("name","n")
                .and(i->i.lt("age",18).or().isNull("email"));
        User user = new User();
        user.setAge(18);
        user.setEmail("dream98job@126.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        //如果想返回List<User>
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        //如果想返回List<Map<String, Object>>
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").le("id",3);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", 1, 2, 3 );
        users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test7() {

        //组装set子句
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("age", 18)
                .set("email", "dream98job@126.com")
                .like("name", "n")
                .and(i -> i.lt("age", 18).or().isNull("email"));
        //lambda表达式内的逻辑优先运算
        //这里必须要创建User对象，否则无法应用自动填充。如果没有自动填充，可以设置为null
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test8() {
        String likeName = "n";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //lambda表达式内的逻辑优先运算
        //User::这种可以避免使用字符串表示字段，防止运行时错误
        queryWrapper.like(StringUtils.isNotBlank(likeName), User::getName,likeName)
                .and(i->i.lt(User::getAge,18).or().isNull(User::getEmail));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test9() {
        //组装set子句
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(User::getAge, 18)
                .set(User::getEmail, "dream98job@126.com")
                .like(User::getName, "n")
                .and(i -> i.lt(User::getAge, 18).or().isNull(User::getEmail)); //lambda表达式内的逻辑优先运算

        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result);
    }
}