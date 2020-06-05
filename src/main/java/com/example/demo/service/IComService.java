package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.pojo.Com;

import com.example.demo.pojo.ComRecruitment;
import com.example.demo.pojo.Job;

public interface IComService {

    // 企业注册 将企业信息存入数据库
    public int reg(Com com);

    // 企业登录 验证原始密码正确性 将新密码存入数据库
    public List<Com> login(String comname);

    // 获取企业信息
    public List<Com> updateCom(String com_id);

    // 修改企业的个人信息 包括基本信息和密码
    public int updateComAll(Com com);

    // 查询企业密码
    public String selectComPwd(Integer com_id);

    // 查询所有已经发布的招聘信息 分页
    public List<Map<String, Object>> findAll(int currentPage, int pageSize, ComRecruitment comRecruitment);

    // 查询总个数
    public int count(ComRecruitment comRecruitment);

    // 通过Recruitment_id删除单条招聘信息
    public int delRecruitment(int recruitment_id);

    // 通过Recruitment_id删除多条招聘信息
    public int delRecruitmentIds(String[] ids);

//	public List<Job> sort();

    // 获取job信息
    public Job jobId(String job_name);

    // 增加一条招聘信息
    public int insert(ComRecruitment comRecruitment);

    // 查询某条招聘信息
    public List<ComRecruitment> comFindInsert(String id);

    public Job jobName(Integer job_id);

    // 修改某一条招聘信息
    public int comUpdate(ComRecruitment comRecruitment);

    // 查询投递简历的信息 分页
    public List<Map<String, Object>> findCheck(int currentPage, int pageSize, Map<String, Object> models);

    // 简历信息未通过
    public int countCheck(Map<String, Object> models);

    // 简历信息通过
    public int comStatus(String userjob_id, String status);

    // 查看求职者的详细简历
    public List<Map<String, Object>> findChecks(int userjob_id);

    // 根据父类id查询职位分类
    public  List<Map<String, Object>> findAllByParentId(int parseInt);

}
