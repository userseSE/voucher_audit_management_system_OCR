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
        if (user == null || user.getActiveName2() == null || user.getUsername() == null || user.getPassword() == null) {
            model.addAttribute("error", "用户名、密码或角色不能为空");
            return "login";
        }

        String activeName2 = user.getActiveName2();
        String storedPassword = userService.searchKey(activeName2, user.getUsername());
        if (!user.getPassword().equals(storedPassword)) {
            model.addAttribute("error", "密码错误，请重新输入！");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("activeName2", activeName2);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("activeName2", activeName2);

        if ("intern".equals(activeName2)) {
            return "indexI";
        } else if ("auditor".equals(activeName2)) {
            return "indexA";
        } else if ("manager".equals(activeName2)) {
            return "indexM";
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
    public Map<String, String> getUserInfo(HttpSession session) {
        Map<String, String> userInfo = new HashMap<>();
        session.setMaxInactiveInterval(30 * 60); // 30 minutes
        String username = (String) session.getAttribute("username");
        String activeName2 = (String) session.getAttribute("activeName2");
        if (username == null || activeName2 == null) {
            return userInfo;
        }

        String name = userService.getUserName(activeName2, username);
        String phone= userService.getPhone(activeName2, username);
        String secretKey=userService.getKey(activeName2, username);

        userInfo.put("username", username);
        userInfo.put("activeName2", activeName2);
        userInfo.put("identity", mapRoleToIdentity(activeName2));
        userInfo.put("name", name);
        userInfo.put("phone",phone);
        userInfo.put("secretKey",secretKey);
        return userInfo;
    }

    private String mapRoleToIdentity(String activeName2) {
        switch (activeName2) {
            case "auditor":
                return "Auditor";
            case "intern":
                return "Intern";
            case "manager":
                return "Manager";
            default:
                return "";
        }
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
