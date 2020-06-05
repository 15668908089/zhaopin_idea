package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserMapper;
import com.example.demo.pojo.User;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    // 用户注册 将用户信息传到数据库
    @Override
    public int reg(User user) {
        userMapper.reg(user);
        User user2=userMapper.selectId(user);
        return userMapper.reg2(user2.getUser_id());
    }

    // 用户登录 通过用户名 查询密码 验证用户
    @Override
    public List<User> login(String username) {
        return userMapper.login(username);
    }

    // 根据城市查询招聘信息
    @Override
    public List<Map<String, Object>> userFindJob(String address) {
        return userMapper.userFindJob(address);
    }

    //	投递简历
    @Override
    public void userTouDi(int user_id, int recruitment_id) {
        userMapper.userTouDi(user_id,recruitment_id);
    }

    @Override
    public String selectUserPwd(Integer user_id) {
        return userMapper.selectUserPwd(user_id);
    }

    @Override
    public int updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public List<Map<String, Object>> findAllUserJob(String user_id) {
        return userMapper.findAllUserJob(Integer.parseInt(user_id));
    }

    @Override
    public int saveUserJob(Map<String, Object> userMap) {
        int i=0;
        int result1=userMapper.updateUser(userMap);
        int list=userMapper.selectUserJob(userMap);
        if (list<=0) {
            userMapper.insertUserJob(userMap);
            i=1;
        }else {
            userMap.put("userjob_id", list);
            userMapper.saveUserJob(userMap);
            i=1;
        }
        return i;
    }

    @Override
    public List<Map<String, Object>> userFindannounces() {
        return userMapper.userFindannounces();
    }

    @Override
    public List<Map<String, Object>> userFindnews() {
        return userMapper.userFindnews();
    }

    @Override
    public int countUser(Map<String, Object> userMap) {
        return userMapper.countUser(userMap);
    }

    @Override
    public List<Map<String, Object>> userFindErJi(int currentPage, int pageSize, Map<String, Object> userMap) {
        return userMapper.userFindErJi(currentPage,pageSize,userMap);
    }

    @Override
    public List<Map<String, Object>> userFindJobType(int parent_id) {
        return userMapper.userFindJobType(parent_id);
    }

    @Override
    public List<Map<String, Object>> findParent(int parent_id) {
        return userMapper.userFindJobType(parent_id);
    }
}
