package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.AdminAnnounce;
import com.example.demo.pojo.AdminNews;
import com.example.demo.pojo.Com;
import com.example.demo.pojo.ComRecruitment;
import com.example.demo.pojo.User;

public interface IAdminService {

    // 查询密码 用于管理员登录
    public List<Admin> login(String admin_name);

    // 获取数据库表中密码 用于和原始密码进行比较
    public String selectAdminPwd(Integer admin_id);

    // 修改管理员的密码
    public int updateAdminAll(Admin admin);

    // 查询普通用户的总条数
    public int countUser(Map<String, Object> userMap);

    // 查询所有的普通用户信息 并对其进行分页
    public List<Map<String, Object>> findAllUser(int currentPage, int pageSize, Map<String, Object> userMap);

    // 根据用户信息表的user_id 删除一条用户信息
    public int delUserById(int user_id);

    // 根据用户信息表的user_id的数组 删除多条用户信息
    public int delUserByIds(String[] ids);

    // 查询某一条的用户信息 根据user_id查询
    public List<User> adminFindUserUpdate(int user_id);

    // 修改某一条的用户信息 根据user_id查询
    public int adminUpdateUser(User user);

    // 根据企业信息表的com_id的数组 删除多条用户信息
    public int delComByIds(String[] ids);

    // 根据企业信息表的com_id 删除一条用户信息
    public int delComById(int com_id);

    // 查询所有的企业信息 并对其进行分页
    public List<Map<String, Object>> findAllCom(int currentPage, int pageSize, Map<String, Object> comMap);

    // 查询企业的总条数
    public int countCom(Map<String, Object> comMap);

    // 查询某一条的企业信息 根据com_id查询
    public List<Com> adminFindComUpdate(int com_id);

    // 修改某一条的企业信息 根据com_id查询
    public int adminUpdateCom(Com com);

    // 统计企业招聘信息的个数
    public int count(Map<String, Object> comRecruitment);

    // 对统计的信息进行分页
    public List<Map<String, Object>> findComRecuitment(int currentPage, int pageSize, Map<String, Object> comRecruitment);

    // 将审核结果传到数据库
    public int adminByComStatus(int recruitment_id, String status);

    // 查询招聘的详细信息
    public List<Map<String, Object>> adminFindCom(int recruitment_id);

    // 搜索所有的新闻信息
    public List<AdminNews> findAllNews(int currentPage, int pageSize, Map<String, Object> adminNews);

    // 搜索新闻的条数
    public int countNews(Map<String, Object> adminNews);

    // 管理员根据新闻表的id值 进行删除
    public int delNews(int news_id);

    // 管理员根据新闻表的id值 进行批量删除
    public int delsNews(String[] ids);

    // 新增新闻
    public int adminNewsInsert(AdminNews news);

    // 查找某条新闻
    public List<AdminNews> adminFindNews(int news_id);

    // 编辑一条新闻
    public int adminNewInsert(AdminNews news);

    // 搜索公告的条数
    public int countAnnounce(Map<String, Object> adminAnnounce);

    // 搜索所有的公告信息
    public List<AdminAnnounce> findAllAnnounce(int curr, int pageSize, Map<String, Object> adminAnnounce);

    // 搜索所有的公告信息
    public int delAnnounce(int announce_id);

    // 管理员根据公告信息表的id值 进行批量删除
    public int delsAnnounce(String[] ids);

    // 新增公告
    public int adminAnnounceInsert(AdminAnnounce announce);

    // 查找某条公告
    public List<AdminAnnounce> adminFindAnnounce(int announce_id);

    // 编辑一条公告
    public int adminAnnUpdate(AdminAnnounce announce);

    public void delUserJobById(int user_id);

    public void delUserJobByIds(String[] ids);

}

