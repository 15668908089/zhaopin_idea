package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.AdminAnnounce;
import com.example.demo.pojo.AdminNews;
import com.example.demo.pojo.Com;
import com.example.demo.pojo.User;
import com.example.demo.service.IAdminService;
import com.example.demo.vo.PageBean;
import com.example.demo.vo.ResponseBean;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    // 管理员根据账号密码进行登录
    @RequestMapping("login")
    public ResponseBean login(@RequestBody Admin admin, HttpSession session) {
        ResponseBean result = new ResponseBean();
        // 根据用户名查询到用户
        List<Admin> adminList = adminService.login(admin.getAdmin_name());
        System.out.println(adminList);
        if (adminList != null && adminList.size() > 0) {
            Admin db_admin = (Admin) adminList.get(0);
            // 如果可以查询到，就比对数据库里密码与页面的密码
            if (db_admin.getAdmin_password().equals(admin.getAdmin_password())) {
                // 如果两个匹配，登录成功
                result.setCode("0");
                result.setMsg("登录成功 ");
                // 可以把用户存到session里备用
                session.setAttribute("com", db_admin);
                result.setList(adminList);
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

    // 管理员修改自己的密码 判读就密码是否正确 修改密码
    @RequestMapping("updatePwd")
    public ResponseBean updateComPwd(@RequestBody Admin admin, String admin_id, String oldpassword) {
        ResponseBean msg = new ResponseBean();
        admin.setAdmin_id(Integer.parseInt(admin_id));
        String admin_password = adminService.selectAdminPwd(admin.getAdmin_id());
        if (admin_password.equals(oldpassword)) {
            msg.setMsg("密码正确！");
            int result = adminService.updateAdminAll(admin);
            if (result > 0) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        } else {
            msg.setMsg("密码输入错误！");
        }
        return msg;
    }

    // 管理员查询用户信息 分页 首先查询总个数 然后进行分页
    @RequestMapping("findAllUser")
    public ResponseBean findAllUser(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestBody Map<String, Object> userMap) {
        if (userMap.get("date").toString().length()>2&&userMap.get("date")!=null) {
//			String day=userMap.get("date").toString();
            StringBuffer day=new StringBuffer(userMap.get("date").toString());
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
            userMap.put("date1", data3);
            System.out.println("11111111"+data3);
            userMap.put("date2",data4);
            System.out.println("22222222"+data4);
        }

        int count = adminService.countUser(userMap);
        PageBean page = new PageBean();
        page.setCount(count);
        List<Map<String, Object>> plist = adminService.findAllUser(currentPage, pageSize, userMap);
        ResponseBean result = new ResponseBean();
        result.setList(plist);
        result.setPage(page);
        return result;

    }

    // 管理员根据用户信息表的id值 进行删除用户
    @RequestMapping("delUserById")
    public String delUserById(String user_id) {
        adminService.delUserJobById(Integer.parseInt(user_id));
        int count = adminService.delUserById(Integer.parseInt(user_id));
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据用户信息表的id值 进行批量删除用户
    @RequestMapping("delUserByIds")
    public String delUserByIds(String[] ids) {
        System.out.println(ids);
        adminService.delUserJobByIds(ids);
        int count = adminService.delUserByIds(ids);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据用户信息表的id值 查询用户信息 用于信息修改
    @RequestMapping("adminFindUserUpdate")
    public ResponseBean adminFindUserUpdate(String user_id) {
        ResponseBean result = new ResponseBean();

        List<User> list = adminService.adminFindUserUpdate(Integer.parseInt(user_id));
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;

    }

    // 管理员修改用户信息
    @RequestMapping("adminUpdateUser")
    public String adminUpdateUser(@RequestBody User user) {
        int count = adminService.adminUpdateUser(user);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员查询企业信息 分页 首先查询总个数 然后进行分页
    @RequestMapping("findAllCom")
    public ResponseBean findAllCom(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestBody Map<String, Object> comMap) {
        int count = adminService.countCom(comMap);
        PageBean page = new PageBean();
        page.setCount(count);
        List<Map<String, Object>> plist = adminService.findAllCom(currentPage, pageSize, comMap);
        ResponseBean result = new ResponseBean();
        result.setList(plist);
        result.setPage(page);
        return result;

    }

    // 管理员根据企业信息表的id值 进行删除企业
    @RequestMapping("delComById")
    public String delComById(String com_id) {
        int count = adminService.delComById(Integer.parseInt(com_id));
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据企业信息表的id值 进行批量删除企业
    @RequestMapping("delComByIds")
    public String delComByIds(String[] ids) {
        int count = adminService.delComByIds(ids);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据企业信息表的id值 查询企业信息 用于信息修改
    @RequestMapping("adminFindComUpdate")
    public ResponseBean adminFindComUpdate(String com_id) {
        ResponseBean result = new ResponseBean();

        List<Com> list = adminService.adminFindComUpdate(Integer.parseInt(com_id));
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;

    }

    // 管理员修改企业信息
    @RequestMapping("adminUpdateCom")
    public String adminUpdateCom(@RequestBody Com com) {
        int count = adminService.adminUpdateCom(com);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 查询企业的招聘信息 对招聘信息进行审核
    @RequestMapping("findComRecuitment")
    public ResponseBean findComRecuitment(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestBody Map<String, Object> comRecruitment) {
        int count = adminService.count(comRecruitment);
        PageBean page = new PageBean();
        page.setPageSize(currentPage);
        page.setCount(count);
        page.setCurrentPage(pageSize);
        int curr = (currentPage - 1) * pageSize;
        List<Map<String, Object>> plist = adminService.findComRecuitment(curr, pageSize, comRecruitment);
        ResponseBean result = new ResponseBean();
        // 可以把用户存到session里备用
        result.setCode("1");
        result.setMsg("查询成功！");
        result.setList(plist);
        result.setPage(page);
        return result;
    }

    // 未通过管理员审核
    @RequestMapping("adminByComStatus1")
    public String adminByComStatus1(String recruitment_id) {
        String status = "未通过";
        int count = adminService.adminByComStatus(Integer.parseInt(recruitment_id), status);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 通过管理员审核
    @RequestMapping("adminByComStatus2")
    public String adminByComStatus2(String recruitment_id) {
        String status = "通过";
        int count = adminService.adminByComStatus(Integer.parseInt(recruitment_id), status);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 查询招聘的详细信息
    @RequestMapping("adminFindCom")
    public ResponseBean findChecks(String recruitment_id) {
        ResponseBean result = new ResponseBean();
        List<Map<String, Object>> list = adminService.adminFindCom(Integer.parseInt(recruitment_id));
        // 如果可以查询到，就比对数据库里密码与页面的密码
        // 如果两个匹配，登录成功
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;

    }

    // 搜索所有的新闻信息
    @RequestMapping("findAllNews")
    public ResponseBean findAllNews(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestBody AdminNews adminNews) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (adminNews.getNews_day() != null) {
            // Date date=new Date(adminNews.getNews_day());
            // System.out.println(date);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String news_day = df.format(adminNews.getNews_day());

            map.put("news_day", news_day);
        }
        map.put("news_name", adminNews.getNews_name());
        System.out.println("news_day" + map.get("news_day"));
        int count = adminService.countNews(map);
        System.out.println(count);
        PageBean page = new PageBean();
        page.setPageSize(currentPage);
        page.setCount(count);
        page.setCurrentPage(pageSize);
        int curr = (currentPage - 1) * pageSize;
        List<AdminNews> plist = adminService.findAllNews(curr, pageSize, map);
        ResponseBean result = new ResponseBean();
        // 可以把用户存到session里备用
        result.setCode("1");
        result.setMsg("查询成功！");
        result.setList(plist);
        result.setPage(page);
        return result;
    }

    // 管理员根据新闻表的id值 进行删除
    @RequestMapping("delNews")
    public String delNews(String news_id) {
        int count = adminService.delNews(Integer.parseInt(news_id));
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据新闻信息表的id值 进行批量删除
    @RequestMapping("delsNews")
    public String delsNews(String[] ids) {
        int count = adminService.delsNews(ids);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 新增新闻
    @RequestMapping("adminNewsInsert")
    public String adminNewsInsert(@RequestBody AdminNews news) {
        int count = adminService.adminNewsInsert(news);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 查找某条新闻
    @RequestMapping("adminFindNews")
    public ResponseBean adminFindNews(String news_id) {
        List<AdminNews> list = adminService.adminFindNews(Integer.parseInt(news_id));
        ResponseBean result = new ResponseBean<>();
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;
    }

    // 编辑一条新闻
    @RequestMapping("adminNewUpdate")
    public String adminNewInsert(@RequestBody AdminNews news) {
        int count = adminService.adminNewInsert(news);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 搜索所有的公告信息
    @RequestMapping("findAllAnnounce")
    public ResponseBean findAllAnnounce(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestBody AdminAnnounce adminAnnounce) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (adminAnnounce.getAnnounce_day() != null) {
            // Date date=new Date(adminNews.getNews_day());
            // System.out.println(date);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String announce_day = df.format(adminAnnounce.getAnnounce_day());
            map.put("announce_day", announce_day);
        }
        map.put("announce_name", adminAnnounce.getAnnounce_name());
        System.out.println("announce_day" + map.get("announce_day"));
        int count = adminService.countAnnounce(map);
        PageBean page = new PageBean();
        page.setPageSize(currentPage);
        page.setCount(count);
        page.setCurrentPage(pageSize);
        int curr = (currentPage - 1) * pageSize;
        List<AdminAnnounce> plist = adminService.findAllAnnounce(curr, pageSize, map);
        ResponseBean result = new ResponseBean();
        // 可以把用户存到session里备用
        result.setCode("1");
        result.setMsg("查询成功！");
        result.setList(plist);
        result.setPage(page);
        return result;
    }

    // 管理员根据公告表的id值 进行删除
    @RequestMapping("delAnnounce")
    public String delAnnounce(String announce_id) {
        int count = adminService.delAnnounce(Integer.parseInt(announce_id));
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 管理员根据公告信息表的id值 进行批量删除
    @RequestMapping("delsAnnounce")
    public String delsAnnounce(String[] ids) {
        int count = adminService.delsAnnounce(ids);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 新增公告
    @RequestMapping("adminAnnounceInsert")
    public String adminAnnounceInsert(@RequestBody AdminAnnounce announce) {
        int count = adminService.adminAnnounceInsert(announce);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    // 查找某条公告
    @RequestMapping("adminFindAnnounce")
    public ResponseBean adminFindNewsAnnounce(String announce_id) {
        List<AdminAnnounce> list = adminService.adminFindAnnounce(Integer.parseInt(announce_id));
        ResponseBean result = new ResponseBean<>();
        result.setCode("0");
        result.setMsg("成功");
        // 可以把用户存到session里备用
        result.setList(list);
        return result;
    }

    // 编辑一条公告
    @RequestMapping("adminAnnUpdate")
    public String adminAnnUpdate(@RequestBody AdminAnnounce announce) {
        int count = adminService.adminAnnUpdate(announce);
        String msg = "";
        if (count > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

}
