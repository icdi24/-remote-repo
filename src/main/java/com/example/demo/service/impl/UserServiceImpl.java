package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getUserByName(String name) {
        // 使用MyBatis-Plus的Wrapper条件构造器进行模糊查询
        // 返回所有匹配的用户
        return userMapper.selectList(
            new QueryWrapper<User>().like("name", name) // like表示模糊匹配
        );
    }
    
    @Override
    public User getUserById(Long id) {
        // 使用MyBatis-Plus的selectById方法根据ID查询用户
        return userMapper.selectById(id);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateById(user) > 0;
    }
}