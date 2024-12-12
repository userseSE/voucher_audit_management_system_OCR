package com.design.spot_check_voucher.service;

import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/01/09/15:38
 * @Description:
 */
public interface UserService {
    int add(User user);
    List<User> queryAll();
    String searchKey(String activeName2, String username);
    public String getUserName(String activeName2, String username);
    public String getPhone(String activeName2, String username);
    public String getKey(String activeName2, String username);
}
