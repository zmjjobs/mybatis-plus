package com.zmj.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmj.mybatisplus.entity.User;
import com.zmj.mybatisplus.mapper.UserMapper;
import com.zmj.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
//ServiceImpl<继承BaseMapper的Mapper, 这个Mapper对应的实体>
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}