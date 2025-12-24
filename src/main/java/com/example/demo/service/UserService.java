package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {


    /**
     * 根据名称查询用户（模糊匹配）
     * @param name 用户名
     * @return 用户列表
     */
    List<User> getUserByName(String name);
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 是否添加成功
     */
    boolean addUser(User user);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Long id);

    /**
     * 修改用户信息
     * @param user 用户对象
     * @return 是否修改成功
     */
    boolean updateUser(User user);
}