package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.pojo.User;

public interface IUserService {

    // 用户登录 通过用户名 查询密码 验证用户
    public int reg(User user);

    // 用户登录 通过用户名 查询密码 验证用户
    public List<User> login(String username);

    // 根据城市查询招聘信息
    public List<Map<String, Object>> userFindJob(String address);

    //	投递简历
    public void userTouDi(int user_id, int recruitment_id);

    public String selectUserPwd(Integer user_id);

    public int updatePwd(User user);

    public List<Map<String, Object>> findAllUserJob(String user_id);

    public int saveUserJob(Map<String, Object> userMap);

    public List<Map<String, Object>> userFindannounces();

    public List<Map<String, Object>> userFindnews();

    public int countUser(Map<String, Object> userMap);

    public List<Map<String, Object>> userFindErJi(int currentPage, int pageSize, Map<String, Object> userMap);

    public List<Map<String, Object>> userFindJobType(int parent_id);

    public List<Map<String, Object>> findParent(int parent_id);

}
