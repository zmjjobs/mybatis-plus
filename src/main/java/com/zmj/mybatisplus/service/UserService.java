package com.zmj.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmj.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listByName(String name);
}