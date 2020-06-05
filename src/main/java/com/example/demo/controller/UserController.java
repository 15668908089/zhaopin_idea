package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.IUserService;
import com.example.demo.vo.PageBean;
import com.example.demo.vo.ResponseBean;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("save")
    public String reg(@RequestBody User user) {
        String msg = "";
        int result = userService.reg(user);
        if (result > 0) {
            msg = "成功";
        } else {
            msg = "失败";
        }
        return msg;
    }

    @RequestMapping("login")
    public ResponseBean login(@RequestBody User user,HttpSession session){
        ResponseBean result=new ResponseBean();
        //根据用户名查询到用户
        List<User> userList=userService.login(user.getUsername());
        System.out.println(userList);
        if(userList!=null&&userList.size()>0){
            User db_user=(User)userList.get(0);
            //如果可以查询到，就比对数据库里密码与页面的密码
            if(db_user.getPassword().equals(user.getPassword())){
                //如果两个匹配，登录成功
                result.setCode("0");
                result.setMsg("登录成功 ");
                //可以把用户存到session里备用
                session.setAttribute("user", db_user);
                result.setList(userList);
            }else{//如果两个不匹配，就提示密码错
                result.setCode("-2");
                result.setMsg("密码错误");
            }
        }else{//如果查询不到，提示用户名错
            result.setCode("-1");
            result.setMsg("用户名不存在");
        }
        return result;
    }

    // 根据城市查询招聘信息
    @RequestMapping("userFindJob")
    public List<Map<String, Object>> userFindJob(String city) {
        List<Map<String, Object>> list = userService.userFindJob(city);
        return list;
    }

    //	投递简历
    @RequestMapping("userTouDi")
    public String userTouDi(String user_id,String recruitment_id) {
        userService.userTouDi(Integer.parseInt(user_id),Integer.parseInt(recruitment_id));
        return "成功";
    }

    // 用户修改自己的密码 判读就密码是否正确 修改密码
    @RequestMapping("updatePwd")
    public String updateComPwd(@RequestBody User user, String user_id, String oldpassword) {
        user.setUser_id(Integer.parseInt(user_id));
        String user_password = userService.selectUserPwd(user.getUser_id());
        String msg="密码输入错误！";
        if (user_password.equals(oldpassword)) {
            msg="密码正确！";
            int result = userService.updatePwd(user);
        }
        return msg;
    }



    @RequestMapping("findAllUserJob")
    public List<Map<String, Object>> findAllUserJob(String user_id) {
        List<Map<String, Object>> list = userService.findAllUserJob(user_id);
        return list;
    }



    @RequestMapping("saveUserJob")
    public String saveUserJob(@RequestBody Map<String, Object> userMap) {
        int list = userService.saveUserJob(userMap);
        return "成功";
    }


    @RequestMapping("userFindannounces")
    public List<Map<String, Object>> userFindannounces() {
        List<Map<String, Object>> list = userService.userFindannounces();
        return list;
    }

    @RequestMapping("userFindnews")
    public List<Map<String, Object>> userFindnews() {
        List<Map<String, Object>> list = userService.userFindnews();
        return list;
    }


    @RequestMapping("userFindErJi")
    public ResponseBean userFindErJi(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                     @RequestBody Map<String, Object> userMap) {
        int count = userService.countUser(userMap);
        PageBean page = new PageBean();
        page.setCount(count);
        int current=(currentPage-1)*pageSize;
        List<Map<String, Object>> list = userService.userFindErJi(current, pageSize, userMap);
        ResponseBean result = new ResponseBean();
        result.setList(list);
        result.setPage(page);
        return result;
    }


    @RequestMapping("userFindJobType")
    public List<Map<String, Object>> userFindJobType(String parent_id) {
        List<Map<String, Object>> list = userService.userFindJobType(Integer.parseInt(parent_id));
        return list;
    }


    @RequestMapping("findParent")
    public List<Map<String, Object>> findParent(String parent_id) {
        List<Map<String, Object>> list = userService.userFindJobType(Integer.parseInt(parent_id));
        return list;
    }
}
