package com.example.demo.domain;

import java.util.List;
import java.util.Map;

import com.example.demo.pojo.Com;
import org.apache.ibatis.annotations.*;


import com.example.demo.pojo.ComRecruitment;
import com.example.demo.pojo.Job;

public interface ComMapper {

    // 企业注册 将企业信息存入数据库
    @Insert("insert into com values(null,#{comname},#{password},#{fullname},#{address},#{comcount},#{email},#{tel},#{description},#{role})")
    public int reg(Com com);

    // 企业登录 验证原始密码正确性 将新密码存入数据库
    @Select("select * from com where comname=#{comname}")
    public List<Com> login(String comname);

    // 获取企业信息 用于修改
    @Select("select * from com where com_id=#{com_id}")
    public List<Com> updateCom(String com_id);

    // 修改企业的个人信息 包括基本信息和密码
    public int updateComAll(Com com);

    // 查询企业密码
    @Select("select password from com where com_id=#{com_id}")
    public String selectComPwd(Integer com_id);

    // 查询所有已经发布的招聘信息 分页
    public List<Map<String, Object>> findAll(int currentPage, int pageSize, @Param("comRecruitment") ComRecruitment comRecruitment);

    // 查询总个数
    //@Select("select count(*) from com_recruitment where com_id=#{com_id}")
    public int count(ComRecruitment comRecruitment);

    // 通过Recruitment_id删除单条招聘信息
    @Delete("delete from com_recruitment where recruitment_id=#{recruitment_id}")
    public int delRecruitment(int recruitment_id);

    // 通过Recruitment_id删除多条招聘信息
    public int delRecruitmentIds(String[] ids);

//	@Select("select DISTINCT job_type from job")
//	public List<Job> sort();

    // @Insert("insert into
    // com_recruitment(recruitment_id,com_id,job_id,degree,language,experience,address,salary,number,requirements,treatment,accumlation,description,examine)values
    // (null,com_id=#{com_id},job_id=#{job_id},degree=#{comRecruitment.degree},language=#{comRecruitment.language},experience=#{comRecruitment.experience},address=#{comRecruitment.address},salary=#{comRecruitment.salary},number=#{comRecruitment.number},requirements=#{comRecruitment.requirements},treatment=#{comRecruitment.treatment},accumlation=#{comRecruitment.accumlation},description=#{comRecruitment.description},examine=#{comRecruitment.examine})")
    // int insert(@Param("comRecruitment") ComRecruitment comRecruitment,
    // @Param("job_id") int job_id,
    // @Param("com_id") String com_id);

    // 获取job信息
    @Select("select * from job where job_name=#{job_name}")
    public Job jobId(String job_name);

    // 增加一条招聘信息
    @Insert("insert into com_recruitment values(null,#{com_id},#{job_id},#{degree},#{language},null,#{experience},#{address},#{salary},#{number},#{requirements},#{treatment},#{accumlation},#{description},#{examine})")
    public int insert(ComRecruitment comRecruitment);

    // 查询某条招聘信息
    @Select("select * from com_recruitment where recruitment_id=#{id}")
    public List<ComRecruitment> comFindInsert(String id);

    @Select("select * from job where job_id=#{job_id}")
    public Job jobName(Integer job_id);

    // 修改某一条招聘信息
    @Update("update com_recruitment set com_id=#{com_id},job_id=#{job_id},degree=#{degree},language=#{language},experience=#{experience},address=#{address},salary=#{salary},number=#{number},requirements=#{requirements},treatment=#{treatment},accumlation=#{accumlation},description=#{description},examine=#{examine} where recruitment_id=#{recruitment_id}")
    public int comUpdate(ComRecruitment comRecruitment);

    // 查询投递简历的信息 分页
    public List<Map<String, Object>> findCheck(int currentPage, int pageSize, @Param("models") Map<String, Object> models);

    // 查询某条 招聘的信息
    //@Select("select count(*) from user_job where com_id=#{com_id}")
    public int countCheck(@Param("models") Map<String, Object> models);

    // 简历信息是否通过
    @Update("update user_com set status=#{status} where id=#{userjob_id}")
    public int comStatus(String userjob_id, String status);

    // 查看求职者的详细简历
    public List<Map<String, Object>> findChecks(int userjob_id);

    @Select("select job_name label,job_id value ,parent_id from job where parent_id=#{parseInt}")
    public List<Map<String, Object>> findAllByParentId(int parseInt);

}
