package com.example.demo.domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.AdminAnnounce;
import com.example.demo.pojo.AdminNews;
import com.example.demo.pojo.Com;

import com.example.demo.pojo.User;


public interface AdminMapper {

    // 查询密码 用于管理员登录
    @Select("select * from admin where admin_name=#{admin_name}")
    public List<Admin> login(String admin_name);

    // 获取数据库表中密码 用于和原始密码进行比较
    @Select("select admin_password from admin where admin_id=#{admin_id}")
    public String selectAdminPwd(Integer admin_id);

    // 修改管理员的密码
    @Update("update admin set admin_password=#{admin_password} where admin_id=#{admin_id}")
    public int updateAdminAll(Admin admin);

    //	// 查询普通用户的总条数
//	@Select("select count(*) from user")
    public int countUser(@Param("userMap") Map<String, Object> userMap);

    // 查询所有的普通用户信息 并对其进行分页
    public List<Map<String, Object>> findAllUser(int currentPage, int pageSize, @Param("userMap") Map<String, Object> userMap);

    // 根据用户信息表的user_id 删除一条用户信息
    @Delete("delete from user where user_id=#{user_id}")
    public int delUserById(int user_id);

    @Delete("delete from user_job where user_id=#{user_id}")
    public void delUserJobById(int user_id);

    // 根据用户信息表的user_id的数组 删除多条用户信息
    public int delUserByIds(String[] ids);

    public int delUserJobByIds(String[] ids);
    // 查询某一条的用户信息 根据user_id查询
    @Select("select * from user where user_id=#{user_id}")
    public List<User> adminFindUserUpdate(int user_id);

    // 修改某一条的用户信息 根据user_id查询
    @Update("update user set realname=#{realname},sex=#{sex},email=#{email},tel=#{tel},birthday=#{birthday},city=#{city},idcard=#{idcard},school=#{school},graduation=#{graduation},major=#{major},degree=#{degree},largnage=#{largnage} where user_id=#{user_id}")
    public int adminUpdateUser(User user);

    // 根据企业信息表的com_id的数组 删除多条用户信息
    public int delComByIds(String[] ids);

    // 根据企业信息表的com_id 删除一条用户信息
    @Delete("delete from com where com_id=#{com_id}")
    public int delComById(int com_id);

    // 查询所有的企业信息 并对其进行分页
    public List<Map<String, Object>> findAllCom(int currentPage, int pageSize, @Param("comMap") Map<String, Object> comMap);

    // 查询企业的总条数
//	@Select("select count(*) from com")
    public int countCom(@Param("comMap") Map<String, Object> comMap);

    // 查询某一条的企业信息 根据com_id查询
    @Select("select * from com where com_id=#{com_id}")
    public List<Com> adminFindComUpdate(int com_id);

    // 修改某一条的企业信息 根据com_id查询
    @Update("update com set fullname=#{fullname},address=#{address},comcount=#{comcount},tel=#{tel},email=#{email},description=#{description} where com_id=#{com_id}")
    public int adminUpdateCom(Com com);

    // 统计企业招聘信息的个数
//	@Select("select count(*) from com_recruitment where examine='未审核'")
    public int count(@Param("comRecruitment") Map<String, Object> comRecruitment);

    // 对统计的信息进行分页
//	@Select("select * from com c,com_recruitment cr,job j where c.com_id=cr.com_id and cr.job_id=j.job_id and cr.examine='未审核' limit #{0},#{1}")
    public List<Map<String, Object>> findComRecuitment(int currentPage, int pageSize,@Param("comRecruitment") Map<String, Object> comRecruitment);

    // 将审核结果传到数据库
    @Update("update com_recruitment set examine=#{examine} where recruitment_id=#{recruitment_id}")
    public int adminByComStatus(int recruitment_id, String examine);

    // 查询招聘的详细信息
    @Select("select cr.*,c.fullname,c.comcount,c.tel,c.email,j.job_name from com c,com_recruitment cr,job j where c.com_id=cr.com_id and cr.job_id=j.job_id and recruitment_id=#{recruitment_id}")
    public List<Map<String, Object>> adminFindCom(int recruitment_id);

    // 搜索所有的新闻信息
//	@Select("select * from admin_news order by news_day desc limit #{0},#{1}")
    public List<AdminNews> findAllNews(int currentPage, int pageSize, @Param("adminNews") Map<String, Object> adminNews);

    // 搜索新闻的条数
//	@Select("select count(*) from admin_news")
    public int countNews(@Param("adminNews") Map<String, Object> adminNews);

    // 管理员根据新闻表的id值 进行删除
    @Delete("delete from admin_news where news_id=#{news_id}")
    public int delNews(int news_id);

    // 管理员根据新闻表的id值 进行批量删除
    public int delsNews(String[] ids);

    // 新增新闻
    @Insert("insert into admin_news values(null,#{news_name},#{news_description},#{news_day})")
    public int adminNewsInsert(AdminNews news);

    // 查找某条新闻
    @Select("select * from admin_news where news_id=#{news_id}")
    public List<AdminNews> adminFindNews(int news_id);

    // 编辑一条新闻
    @Update("update admin_news set news_name=#{news_name},news_description=#{news_description},news_day=#{news_day} where news_id=#{news_id}")
    public int adminNewInsert(AdminNews news);

    // 搜索公告的条数
//	@Select("select count(*) from admin_announce")
    public int countAnnounce(@Param("adminAnnounce") Map<String, Object> adminAnnounce);

    // 搜索所有的公告信息
//	@Select("select * from admin_announce order by announce_day desc  limit #{0},#{1}")
    public List<AdminAnnounce> findAllAnnounce(int currentPage, int pageSize,@Param("adminAnnounce") Map<String, Object> adminAnnounce);

    // 管理员根据公告表的id值 进行删除
    @Delete("delete from admin_announce where announce_id=#{announce_id}")
    public int delAnnounce(int announce_id);

    // 管理员根据公告信息表的id值 进行批量删除
    public int delsAnnounce(String[] ids);

    // 新增公告
    @Insert("insert into admin_announce values(null,#{announce_name},#{announce_description},#{announce_day})")
    public int adminAnnounceInsert(AdminAnnounce announce);

    // 查找某条公告
    @Select("select * from admin_announce where announce_id=#{announce_id}")
    public List<AdminAnnounce> adminFindAnnounce(int announce_id);

    // 编辑一条公告
    @Update("update admin_announce set announce_name=#{announce_name}, announce_description=#{announce_description},announce_day=#{announce_day} where announce_id=#{announce_id}")
    public int adminAnnUpdate(AdminAnnounce announce);



}
