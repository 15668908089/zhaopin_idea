package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ComMapper;
import com.example.demo.pojo.Com;

import com.example.demo.pojo.ComRecruitment;
import com.example.demo.pojo.Job;

@Service
public class ComServiceImpl implements IComService {
    @Autowired
    ComMapper comMapper;

    // 企业注册 将企业信息存入数据库
    @Override
    public int reg(Com com) {
        return comMapper.reg(com);
    }

    // 企业登录 验证原始密码正确性 将新密码存入数据库
    @Override
    public List<Com> login(String comname) {
        return comMapper.login(comname);
    }

    // 获取企业信息 用于修改
    @Override
    public List<Com> updateCom(String com_id) {
        return comMapper.updateCom(com_id);
    }

    // 修改企业的个人信息 包括基本信息和密码
    @Override
    public int updateComAll(Com com) {
        return comMapper.updateComAll(com);
    }

    // 查询企业密码
    @Override
    public String selectComPwd(Integer com_id) {
        return comMapper.selectComPwd(com_id);
    }

    // 查询所有已经发布的招聘信息 分页
    @Override
    public List<Map<String, Object>> findAll(int currentPage, int pageSize, ComRecruitment comRecruitment) {
        int curr = (currentPage - 1) * pageSize;
        return comMapper.findAll(curr, pageSize, comRecruitment);
    }

    // 查询总个数
    @Override
    public int count(ComRecruitment comRecruitment) {
        return comMapper.count(comRecruitment);
    }

    // 通过Recruitment_id删除单条招聘信息
    @Override
    public int delRecruitment(int recruitment_id) {
        return comMapper.delRecruitment(recruitment_id);
    }

    // 通过Recruitment_id删除多条招聘信息
    @Override
    public int delRecruitmentIds(String[] ids) {
        return comMapper.delRecruitmentIds(ids);
    }

//	@Override
//	public List<Job> sort() {
//		return comMapper.sort();
//	}

    // 获取job信息
    @Override
    public Job jobId(String job_name) {
        return comMapper.jobId(job_name);
    }

    // 增加一条招聘信息
    @Override
    public int insert(ComRecruitment comRecruitment) {
        return comMapper.insert(comRecruitment);
    }

    // 查询某条招聘信息
    @Override
    public List<ComRecruitment> comFindInsert(String id) {
        return comMapper.comFindInsert(id);
    }

    @Override
    public Job jobName(Integer job_id) {
        return comMapper.jobName(job_id);
    }

    // 修改某一条招聘信息
    @Override
    public int comUpdate(ComRecruitment comRecruitment) {
        return comMapper.comUpdate(comRecruitment);
    }

    // 查询投递简历的信息 分页
    @Override
    public List<Map<String, Object>> findCheck(int currentPage, int pageSize, Map<String, Object> models) {
        int curr = (currentPage - 1) * pageSize;
        return comMapper.findCheck(curr, pageSize, models);
    }

    // 查询某条 招聘的信息
    @Override
    public int countCheck(Map<String, Object> models) {
        return comMapper.countCheck(models);
    }

    // 简历信息是否通过
    @Override
    public int comStatus(String userjob_id, String status) {
        return comMapper.comStatus(userjob_id, status);
    }

    // 查看求职者的详细简历
    @Override
    public List<Map<String, Object>> findChecks(int userjob_id) {
        return comMapper.findChecks(userjob_id);
    }

    @Override
    public  List<Map<String, Object>> findAllByParentId(int parseInt) {
        return comMapper.findAllByParentId(parseInt);
    }



}
