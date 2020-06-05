package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.AdminAnnounce;
import com.example.demo.pojo.AdminNews;
import com.example.demo.pojo.Com;
import com.example.demo.pojo.User;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminMapper adminMapper;

    // 查询密码 用于管理员登录
    @Override
    public List<Admin> login(String admin_name) {
        return adminMapper.login(admin_name);
    }

    // 获取数据库表中密码 用于和原始密码进行比较
    @Override
    public String selectAdminPwd(Integer admin_id) {
        return adminMapper.selectAdminPwd(admin_id);
    }

    // 修改管理员的密码
    @Override
    public int updateAdminAll(Admin admin) {
        return adminMapper.updateAdminAll(admin);
    }

    // 查询普通用户的总条数
    @Override
    public int countUser(Map<String, Object> userMap) {
        return adminMapper.countUser(userMap);
    }

    // 查询所有的普通用户信息 并对其进行分页
//	@Override
//	public List<Map<String, Object>> findAllUser(int currentPage, int pageSize,Map<String, Object> userMap) {
//		int curr = (currentPage - 1) * pageSize;
//		return adminMapper.findAllUser(curr, pageSize,userMap);
//	}

    @Override
    public List<Map<String, Object>> findAllUser(int currentPage, int pageSize, Map<String, Object> userMap) {
        int curr = (currentPage - 1) * pageSize;
        return adminMapper.findAllUser(curr, pageSize,userMap);
    }

    // 根据用户信息表的user_id 删除一条用户信息
    @Override
    public int delUserById(int user_id) {
        return adminMapper.delUserById(user_id);
    }

    // 根据用户信息表的user_id的数组 删除多条用户信息
    @Override
    public int delUserByIds(String[] ids) {
        return adminMapper.delUserByIds(ids);
    }

    // 查询某一条的用户信息 根据user_id查询
    @Override
    public List<User> adminFindUserUpdate(int user_id) {
        return adminMapper.adminFindUserUpdate(user_id);
    }

    // 修改某一条的用户信息 根据user_id查询
    @Override
    public int adminUpdateUser(User user) {
        return adminMapper.adminUpdateUser(user);
    }

    // 根据企业信息表的com_id的数组 删除多条用户信息
    @Override
    public int delComByIds(String[] ids) {
        return adminMapper.delComByIds(ids);
    }

    // 根据企业信息表的com_id 删除一条用户信息
    @Override
    public int delComById(int com_id) {
        return adminMapper.delComById(com_id);
    }

    // 查询所有的企业信息 并对其进行分页
    @Override
    public List<Map<String, Object>> findAllCom(int currentPage, int pageSize,Map<String, Object> comMap) {
        int curr = (currentPage - 1) * pageSize;
        return adminMapper.findAllCom(curr, pageSize,comMap);
    }

    // 查询企业的总条数
    @Override
    public int countCom(Map<String, Object> comMap) {
        return adminMapper.countCom(comMap);
    }

    // 查询某一条的企业信息 根据com_id查询
    @Override
    public List<Com> adminFindComUpdate(int com_id) {
        return adminMapper.adminFindComUpdate(com_id);
    }

    // 修改某一条的企业信息 根据com_id查询
    @Override
    public int adminUpdateCom(Com com) {
        return adminMapper.adminUpdateCom(com);
    }

    // 统计企业招聘信息的个数
    @Override
    public int count(Map<String, Object> comRecruitment) {
        return adminMapper.count(comRecruitment);
    }

    // 对统计的信息进行分页
    @Override
    public List<Map<String, Object>> findComRecuitment(int currentPage, int pageSize,Map<String, Object> comRecruitment) {
        return adminMapper.findComRecuitment(currentPage, pageSize,comRecruitment);
    }

    // 将审核结果传到数据库
    @Override
    public int adminByComStatus(int recruitment_id, String status) {
        return adminMapper.adminByComStatus(recruitment_id, status);
    }

    // 查询招聘的详细信息
    @Override
    public List<Map<String, Object>> adminFindCom(int recruitment_id) {
        return adminMapper.adminFindCom(recruitment_id);
    }

    // 搜索所有的新闻信息
    @Override
    public List<AdminNews> findAllNews(int currentPage, int pageSize,Map<String, Object> adminNews) {
        return adminMapper.findAllNews(currentPage, pageSize,adminNews);
    }

    // 搜索新闻的条数
    @Override
    public int countNews(Map<String, Object> adminNews) {
        return adminMapper.countNews(adminNews);
    }

    // 管理员根据新闻表的id值 进行删除
    @Override
    public int delNews(int news_id) {
        return adminMapper.delNews(news_id);
    }

    // 管理员根据新闻表的id值 进行批量删除
    @Override
    public int delsNews(String[] ids) {
        return adminMapper.delsNews(ids);
    }

    // 新增新闻
    @Override
    public int adminNewsInsert(AdminNews news) {
        return adminMapper.adminNewsInsert(news);
    }

    // 查找某条新闻
    @Override
    public List<AdminNews> adminFindNews(int news_id) {
        return adminMapper.adminFindNews(news_id);
    }

    // 编辑一条新闻
    @Override
    public int adminNewInsert(AdminNews news) {
        return adminMapper.adminNewInsert(news);
    }

    // 搜索公告的条数
    @Override
    public int countAnnounce(Map<String, Object> adminAnnounce) {
        return adminMapper.countAnnounce(adminAnnounce);
    }

    // 搜索所有的公告信息
    @Override
    public List<AdminAnnounce> findAllAnnounce(int currentPage, int pageSize,Map<String, Object> adminAnnounce) {
        return adminMapper.findAllAnnounce(currentPage,pageSize,adminAnnounce);
    }

    // 搜索所有的公告信息
    @Override
    public int delAnnounce(int announce_id) {
        return adminMapper.delAnnounce(announce_id);
    }

    // 管理员根据公告信息表的id值 进行批量删除
    @Override
    public int delsAnnounce(String[] ids) {
        return adminMapper.delsAnnounce(ids);
    }

    // 新增公告
    @Override
    public int adminAnnounceInsert(AdminAnnounce announce) {
        return adminMapper.adminAnnounceInsert(announce);
    }

    // 查找某条公告
    @Override
    public List<AdminAnnounce> adminFindAnnounce(int announce_id) {
        return adminMapper.adminFindAnnounce(announce_id);
    }

    // 编辑一条公告
    @Override
    public int adminAnnUpdate(AdminAnnounce announce) {
        return adminMapper.adminAnnUpdate(announce);
    }

    @Override
    public void delUserJobById(int user_id) {
        adminMapper.delUserJobById(user_id);

    }

    @Override
    public void delUserJobByIds(String[] ids) {
        adminMapper.delUserJobByIds(ids);

    }



}
