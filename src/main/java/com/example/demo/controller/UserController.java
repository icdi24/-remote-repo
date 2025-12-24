package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") // 前端 Vue 端口，需与实际前端端口一致
public class UserController {

    // 注入 Service 接口（Spring 自动生成代理对象，无需手动实例化）
    @Autowired
    private UserService userService;

    /**
     * 接口1：根据Name查询用户（对应前端 fetchUser 函数）
     * 请求方式：GET
     * 请求路径：/api/user/check/{name}（name 为路径参数）
     */
    @GetMapping("/check/{name}")
    public Map<String, Object> getUserByName(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        // 调用 Service 方法查询用户
        List<User> userList = userService.getUserByName(name);

        if (userList != null && !userList.isEmpty()) {
            result.put("code", 200); // 成功状态码
            result.put("msg", "查询成功，共 " + userList.size() + " 条匹配记录");
            result.put("data", userList); // 返回用户列表数据（前端接收后渲染）
        } else {
            result.put("code", 404); // 未找到状态码
            result.put("msg", "未找到匹配的用户");
            result.put("data", new ArrayList<>()); // 返回空列表
        }
        return result;
    }

    /**
     * 根据ID查询用户
     * 请求方式：GET
     * 请求路径：/api/user/check/id/{id}（id 为路径参数）
     */
    @GetMapping("/check/id/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        // 调用 Service 方法根据ID查询用户
        User user = userService.getUserById(id);

        if (user != null) {
            result.put("code", 200); // 成功状态码
            result.put("msg", "查询成功");
            result.put("data", user); // 返回用户数据
        } else {
            result.put("code", 404); // 未找到状态码
            result.put("msg", "未找到匹配的用户");
            result.put("data", null);
        }
        return result;
    }

    /**
     * 接口2：添加用户（对应前端 handleAddUser 函数）
     * 请求方式：POST
     * 请求路径：/api/user
     * 请求体：User 对象（前端传递的 form 数据：name、avatar）
     */
    @PostMapping
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 调用 Service 方法新增用户
            boolean success = userService.addUser(user);

            if (success) {
                result.put("code", 200);
                result.put("msg", "添加用户成功");
                result.put("data", user); // 返回新增用户（包含自增的 id）
            } else {
                result.put("code", 500);
                result.put("msg", "添加用户失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "添加失败：" + e.getMessage());
        }
        return result;
    }
    /**
     * 接口3：查询所有用户
     * 请求方式：GET
     * 请求路径：/api/user/all
     * 无请求参数，返回所有用户列表
     */
    @GetMapping("/all")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            // 调用 Service 方法查询所有用户
            List<User> userList = userService.getAllUsers();

            // 无论列表是否为空，都返回 200（查询操作本身成功）
            result.put("code", 200);
            if (userList.isEmpty()) {
                result.put("msg", "查询成功，暂无用户数据");
            } else {
                result.put("msg", "查询成功，共 " + userList.size() + " 条用户数据");
            }
            result.put("data", userList); // 返回用户列表（前端可遍历渲染）
        } catch (Exception e) {
            // 捕获查询异常（如数据库连接失败、SQL 错误等）
            result.put("code", 500);
            result.put("msg", "查询所有用户失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 调用 Service 方法删除用户
            boolean success = userService.deleteUser(id);

            if (success) {
                result.put("code", 200);
                result.put("msg", "删除用户成功");
            } else {
                result.put("code", 404);
                result.put("msg", "用户不存在，删除失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "删除用户失败：" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{id}")
    public Map<String, Object> updateUser(@RequestBody User user,@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 调用 Service 方法更新用户
            boolean success = userService.updateUser(user);
            if (success) {
                result.put("code", 200);
                result.put("msg", "更改用户成功");

            }else {
                result.put("code", 404);
                result.put("msg", "用户不存在，更改失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "更改用户失败：" + e.getMessage());
        }
        return result;
    }
}