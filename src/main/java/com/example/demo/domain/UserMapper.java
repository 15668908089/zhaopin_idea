package com.example.demo.domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.example.demo.pojo.User;


public interface UserMapper {

    // 用户注册 将用户信息传到数据库
    @Insert("insert into user values(null,#{username},#{password},#{realname},#{sex},#{email},#{tel},#{birthday},#{idcard},#{school},#{graduation},#{degree},#{largnage},#{city},#{major})")
    public int reg(User user);

    @Insert("insert into user_job (userjob_id,user_id) values(null,#{user_id})")
    public int reg2(int user_id);

    @Select("select * from user where username=#{username} and password=#{password} and realname=#{realname}")
    public User selectId(User user);
    // 用户登录 通过用户名 查询密码 验证用户
    @Select("select * from user where username=#{username}")
    public List<User> login(String username);

    // 根据城市查询招聘信息
    public List<Map<String, Object>> userFindJob(@Param("address") String address);

    @Insert("insert into user_com values(null,#{user_id},#{recruitment_id},'未审核')")
    public void userTouDi(int user_id, int recruitment_id);

    // 获取数据库表中密码 用于和原始密码进行比较
    // 获取数据库表中密码 用于和原始密码进行比较
    @Select("select password from user where user_id=#{user_id}")
    public String selectUserPwd(Integer user_id);
    // 修改用户的密码
    @Update("update user set password=#{password} where user_id=#{user_id}")
    public int updatePwd(User user);

    @Select("select * from user u,user_job uj where u.user_id=uj.user_id and u.user_id=#{user_id}")
    public List<Map<String, Object>> findAllUserJob(int user_id);

    @Update("update user set realname=#{realname}"
            + " ,sex=#{sex} ,email=#{email} ,"
            + "tel=#{tel},birthday=#{birthday},"
            + "idcard=#{idcard},school=#{school},"
            + "graduation=#{graduation},degree=#{degree},"
            + "largnage=#{largnage},city=#{city},major=#{major}"
            + " where user_id=#{user_id}")
    public int updateUser(Map<String, Object> userMap);

    @Select("select userjob_id from user_job where user_id=#{user_id}")
    public int selectUserJob(Map<String, Object> userMap);

    @Insert("insert into user_job values(null,#{user_id},#{place},#{description},null,#{project})")
    public void insertUserJob(Map<String, Object> userMap);

    @Update("update user_job set user_id=#{user_id},place=#{place},description=#{description},project=#{project}"
            + "where userjob_id=#{userjob_id} ")
    public void saveUserJob(Map<String, Object> userMap);

    @Select("select * from admin_announce order by announce_day desc limit 0,10")
    public List<Map<String, Object>> userFindannounces();

    @Select("select * from admin_news order by news_day desc limit 0,7")
    public List<Map<String, Object>> userFindnews();


    public int countUser(@Param("userMap") Map<String, Object> userMap);

    public List<Map<String, Object>> userFindErJi(int currentPage, int pageSize,@Param("userMap") Map<String, Object> userMap);

    @Select("select * from job where parent_id=#{parent_id}")
    public List<Map<String, Object>> userFindJobType(int parent_id);

    @Select("select * from job where parent_id=#{parent_id}")
    public List<Map<String, Object>> findParent(int parent_id);




}

