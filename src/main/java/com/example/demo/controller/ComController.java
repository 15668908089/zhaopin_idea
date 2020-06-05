package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Com;
import com.example.demo.pojo.ComRecruitment;
import com.example.demo.pojo.Job;
import com.example.demo.service.IComService;
import com.example.demo.vo.PageBean;
import com.example.demo.vo.ResponseBean;

@RestController
@RequestMapping("com")
public class ComController {

    @Autowired
    IComService comService;

    // 企业注册 将企业信息存入数据库
    @RequestMapping("save")
    public String reg(@RequestBody Com com) {
        String msg = "";
        int result = comService.reg(com);
        if (result > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 企业登录 验证原始密码正确性 将新密码存入数据库
    @RequestMapping("login")
    public ResponseBean login(@RequestBody Com com, HttpSession session) {
        ResponseBean result = new ResponseBean();
        // 根据用户名查询到用户
        List<Com> comList = comService.login(com.getComname());
        System.out.println(comList);
        if (comList != null && comList.size() > 0) {
            Com db_com = (Com) comList.get(0);
            // 如果可以查询到，就比对数据库里密码与页面的密码
            if (db_com.getPassword().equals(com.getPassword())) {
                // 如果两个匹配，登录成功
                result.setCode("0");
                result.setMsg("登录成功 ");
                // 可以把用户存到session里备用
                session.setAttribute("com", db_com);
                result.setList(comList);
            } else {// 如果两个不匹配，就提示密码错
                result.setCode("-2");
                result.setMsg("密码错误");
            }
        } else {// 如果查询不到，提示用户名错
            result.setCode("-1");
            result.setMsg("用户名不存在");
        }
        return result;
    }

    // 修改企业的个人信息 包括基本信息和密码
    @RequestMapping("updateComAll")
    public ResponseBean updateComPwd(@RequestBody Com com, String com_id, String oldpassword) {
        ResponseBean msg = new ResponseBean();
        com.setCom_id(Integer.parseInt(com_id));
        if (com.getPassword() != null) {
            String password = comService.selectComPwd(com.getCom_id());
            // 如果可以查询到，就比对数据库里密码与页面的密码
            System.out.println("mima:" + password);
            System.out.println(oldpassword);
            if (password.equals(oldpassword)) {
                msg.setMsg("密码正确！");
                System.out.println("id=" + com.getCom_id());
                int result = comService.updateComAll(com);
                if (result > 0) {
                    System.out.println("成功");
                } else {
                    System.out.println("失败");
                }
            } else {
                msg.setMsg("密码输入错误！");
            }
        } else {
            System.out.println("id=" + com.getCom_id());
            int result = comService.updateComAll(com);
            if (result > 0) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        }
        return msg;
    }

    // 查询企业的个人信息
    @RequestMapping("updateCom")
    public ResponseBean updatecom(String com_id) {
        ResponseBean result = new ResponseBean();
        // 根据用户名查询到用户
        System.out.println(com_id);
        List<Com> comList = comService.updateCom(com_id);
        System.out.println(comList);
        Com db_com = (Com) comList.get(0);
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        result.setCode("0");
        result.setMsg("登录成功 ");
        // 可以把用户存到session里备用
        result.setList(comList);
        return result;
    }

    // 查询所有已经发布的招聘信息 首先查询总个数 然后分页
    @RequestMapping("findAll")
    public ResponseBean findAll(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestBody ComRecruitment comRecruitment) {
        int count = comService.count(comRecruitment);
        PageBean page = new PageBean();
        page.setPageSize(currentPage);
        page.setCount(count);
        page.setCurrentPage(pageSize);
        List<Map<String, Object>> plist = comService.findAll(currentPage, pageSize, comRecruitment);
        ResponseBean result = new ResponseBean();
        // 可以把用户存到session里备用
        result.setCode("1");
        result.setMsg("查询成功！");
        result.setList(plist);
        result.setPage(page);
        return result;

    }

    // 通过Recruitment_id删除单条招聘信息
    @RequestMapping("delRecruitment")
    public String delRecruitment(String recruitment_id) {
        int count = comService.delRecruitment(Integer.parseInt(recruitment_id));
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 通过Recruitment_id删除多条招聘信息
    @RequestMapping("delRecruitmentIds")
    public String delRecruitmentIds(String[] ids) {
        System.out.println(ids);
        int count = comService.delRecruitmentIds(ids);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 增加一条招聘信息
    @RequestMapping("comInsert")
    public String insert(@RequestBody Map<String, Object> models) {

        System.out.println(models);
        System.out.println(models.get("degree").toString());
        ComRecruitment comRecruitment = new ComRecruitment();
        comRecruitment.setDegree(models.get("degree").toString());
        comRecruitment.setLanguage(models.get("language").toString());
        comRecruitment.setExperience(models.get("experience").toString());
        comRecruitment.setAddress(models.get("address").toString());
        comRecruitment.setSalary(models.get("salary").toString());
        comRecruitment.setNumber(models.get("number").toString());
        comRecruitment.setRequirements(models.get("requirements").toString());
        comRecruitment.setTreatment(models.get("treatment").toString());
        comRecruitment.setAccumlation(models.get("accumlation").toString());
        comRecruitment.setDescription(models.get("description").toString());

        Job job = new Job();
        job.setJob_name(models.get("job_name").toString());
        job = comService.jobId(job.getJob_name());
        comRecruitment.setJob_id(job.getJob_id());
        comRecruitment.setCom_id(Integer.parseInt(models.get("com_id").toString()));
        int count = comService.insert(comRecruitment);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 修改某一条招聘信息
    @RequestMapping("comUpdate")
    public String comUpdate(@RequestBody Map<String, Object> models) {

        System.out.println(models);
        System.out.println(models.get("degree").toString());
        ComRecruitment comRecruitment = new ComRecruitment();
        comRecruitment.setDegree(models.get("degree").toString());
        comRecruitment.setLanguage(models.get("language").toString());
        comRecruitment.setExperience(models.get("experience").toString());
        comRecruitment.setAddress(models.get("address").toString());
        comRecruitment.setSalary(models.get("salary").toString());
        comRecruitment.setNumber(models.get("number").toString());
        comRecruitment.setRequirements(models.get("requirements").toString());
        comRecruitment.setTreatment(models.get("treatment").toString());
        comRecruitment.setAccumlation(models.get("accumlation").toString());
        comRecruitment.setDescription(models.get("description").toString());
        comRecruitment.setRecruitment_id(Integer.parseInt(models.get("recruitment_id").toString()));
        Job job = new Job();
        job.setJob_name(models.get("job_name").toString());
        job = comService.jobId(job.getJob_name());
        comRecruitment.setJob_id(job.getJob_id());
        comRecruitment.setCom_id(Integer.parseInt(models.get("com_id").toString()));
        System.out.println("id:___》" + comRecruitment.getJob_id());
        int count = comService.comUpdate(comRecruitment);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

//	// 查询职位总类
//	@RequestMapping("sort")
//	public ResponseBean sort() {
//		ResponseBean result = new ResponseBean();
//		List<Job> jobList = comService.sort();
//		System.out.println(jobList);
//		// 如果可以查询到，就比对数据库里密码与页面的密码
//		// 如果两个匹配，登录成功
//		result.setCode("0");
//		result.setMsg("登录成功 ");
//		// 可以把用户存到session里备用
//		result.setList(jobList);
//		return result;
//
//	}

    // 查询某条 招聘的信息
    @RequestMapping("comFindInsert")
    public ResponseBean comFindInsert(String id) {
        ResponseBean result = new ResponseBean();
        List<ComRecruitment> list = comService.comFindInsert(id);
        System.out.println(list.get(0).getJob_id());
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        Job job = comService.jobName(list.get(0).getJob_id());
        result.setCode("0");
        result.setMsg(job.getJob_name());
        // 可以把用户存到session里备用
        result.setList(list);
        return result;

    }

    // 查询投递简历的信息 首先确定总条数 然后分页
    @RequestMapping("findCheck")
    public ResponseBean findCheck(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestBody Map<String, Object> models) {
        if (models.get("date").toString().length()>2&&models.get("date")!=null) {
//			String day=userMap.get("date").toString();
            StringBuffer day=new StringBuffer(models.get("date").toString());
            String[] arr=day.substring(1, day.length()-1) .split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data1=null;
            Date data2=null;
            try {
                data1 = sdf.parse(arr[0]);
                data2=sdf.parse(arr[1]);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(data1);
            c.add(Calendar.DAY_OF_MONTH, 1);
            String data3=sdf.format(c.getTime());
            c.setTime(data2);
            c.add(Calendar.DAY_OF_MONTH, 1);
            String data4=sdf.format(c.getTime());
            models.put("date1", data3);
            System.out.println("11111111"+data3);
            models.put("date2",data4);
            System.out.println("22222222"+data4);
        }
        int count = comService.countCheck(models);
        PageBean page = new PageBean();
        page.setPageSize(currentPage);
        page.setCount(count);
        page.setCurrentPage(pageSize);
        List<Map<String, Object>> plist = comService.findCheck(currentPage, pageSize, models);
        ResponseBean result = new ResponseBean();
        // 可以把用户存到session里备用
        result.setCode("1");
        result.setMsg("查询成功！");
        result.setList(plist);
        result.setPage(page);
        return result;

    }

    // 简历信息是未通过
    @RequestMapping("comStatus1")
    public String comStatus1(String userjob_id) {
        String status = "未通过";
        int count = comService.comStatus(userjob_id, status);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 简历信息通过
    @RequestMapping("comStatus2")
    public String comStatus2(String userjob_id) {
        String status = "通过";
        int count = comService.comStatus(userjob_id, status);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 查看求职者的详细简历
    @RequestMapping("findChecks")
    public ResponseBean findChecks(String userjob_id) {
        ResponseBean result = new ResponseBean();
        List<Map<String, Object>> list = comService.findChecks(Integer.parseInt(userjob_id));
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;

    }

//	根据父类id查询职位分类

    @RequestMapping("findAllByParentId")
    public List<Map<String, Object>> findAllByParentId(String parent_id) {
        List<Map<String, Object>> list = comService.findAllByParentId(Integer.parseInt(parent_id));
        return list;
    }
}
