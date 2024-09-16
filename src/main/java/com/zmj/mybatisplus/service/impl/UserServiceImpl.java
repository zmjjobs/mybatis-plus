package com.zmj.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmj.mybatisplus.entity.User;
import com.zmj.mybatisplus.mapper.UserMapper;
import com.zmj.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//ServiceImpl<继承BaseMapper的Mapper, 这个Mapper对应的实体>
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> listByName(String name) {
        // baseMapper对象指向当前业务的mapper对象
        return baseMapper.selectByName(name);
    }
}