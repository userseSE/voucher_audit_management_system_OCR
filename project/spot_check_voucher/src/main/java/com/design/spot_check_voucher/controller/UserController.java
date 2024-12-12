package com.design.spot_check_voucher.controller;

import com.design.spot_check_voucher.common.MessageConstant;
import com.design.spot_check_voucher.common.Result;
import com.design.spot_check_voucher.common.StatusCode;
import com.design.spot_check_voucher.domain.User;
import com.design.spot_check_voucher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static org.apache.ibatis.ognl.DynamicSubscript.all;
import static sun.misc.Version.println;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/01/09/13:33
 * @Description:
 */
@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/LoginSuccess")
    @PostMapping("indexI")
    public String LoginSuccess(HttpServletRequest request, Model model, User user) {
        if (user.getActiveName2().equals("intern")) {
            //ActiveName2是用户的身份
            if (user.getPassword().equals(userService.searchKey(user.getActiveName2(),user.getUsername()))) {
                // 将用户信息存储在会话中
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("activeName2", "intern");
                String username = (String) session.getAttribute("username");
                String activeName2 = (String) session.getAttribute("activeName2");

                model.addAttribute("username", username);
                model.addAttribute("activeName2", activeName2);
                return "indexI";

            } else {
                model.addAttribute("error", "密码错误，请重新输入！");
                return "login";
            }
        } else if (user.getActiveName2().equals("auditor")) {
            if (user.getPassword().equals(userService.searchKey(user.getActiveName2(),user.getUsername()))) {
                // 将用户信息存储在会话中
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("activeName2", "auditor");
                String username = (String) session.getAttribute("username");
                String activeName2 = (String) session.getAttribute("activeName2");

                model.addAttribute("username", username);
                model.addAttribute("activeName2", activeName2);
                return "indexA";

            } else {
                model.addAttribute("error", "密码错误，请重新输入！");
                return "login";
            }
        } else if (user.getActiveName2().equals("manager")) {
            if (user.getPassword().equals(userService.searchKey(user.getActiveName2(),user.getUsername()))) {
                // 将用户信息存储在会话中
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("activeName2", "manager");
                String username = (String) session.getAttribute("username");
                String activeName2 = (String) session.getAttribute("activeName2");

                model.addAttribute("username", username);
                model.addAttribute("activeName2", activeName2);

                return "indexM";

            } else {
                model.addAttribute("error", "密码错误，请重新输入！");
                return "login";
            }
        } else {
            return "0";
        }
    }

/*
    @GetMapping("/getUserInfo")
    @ResponseBody
    public Map<String, String> getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String activeName2 = (String) session.getAttribute("activeName2");

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", username);
        userInfo.put("activeName2", activeName2);

        return userInfo;
    }
*/

    @GetMapping("/getUserInfo")
    @ResponseBody
    public Map<String, String> getUserInfo(HttpServletRequest request,HttpSession session) {
        Map<String, String> userInfo = new HashMap<>();
        session.setMaxInactiveInterval(30); // 设置session的超时时间为30分钟 * 60
        Boolean temp=session.isNew();
        if (session == null) {
            // session不存在，进行相应的处理，例如返回登录页面
        } else {
            // session存在，继续进行后续操作
            String username = (String) session.getAttribute("username");
            String activeName2 = (String) session.getAttribute("activeName2");
            // ...
            // 添加username和activeName2到userInfo中
            userInfo.put("username", username);
            userInfo.put("activeName2", activeName2);

            // 根据 activeName2 查询对应的身份名称
            String identity = "";
            switch (activeName2) {
                case "auditor":
                    identity = "审计师";
                    break;
                case "intern":
                    identity = "实习生";
                    break;
                case "manager":
                    identity = "管理员";
                    break;
                default:
                    break;
            }

            // 根据 username 和 activeName2 查询对应的员工姓名
            String name = userService.getUserName(activeName2, username);
            String phone= userService.getPhone(activeName2, username);
            String secretKey=userService.getKey(activeName2, username);

            userInfo.put("username", username);
            userInfo.put("identity", identity);
            userInfo.put("name", name);
            userInfo.put("phone",phone);
            userInfo.put("secretKey",secretKey);
        }
        return userInfo;
    }

    @RequestMapping("/toShow")
    @GetMapping("showAll")
    public String showAll(Model model){
        List<User> test=userService.queryAll();
        model.addAttribute("users",userService.queryAll());
        return "showAll";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/registerSuccess")
    public String RegisterSuccess(User user){
        int add = userService.add(user);
        return "login";
    }

}