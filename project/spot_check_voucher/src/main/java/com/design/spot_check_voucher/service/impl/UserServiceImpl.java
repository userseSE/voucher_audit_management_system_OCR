package com.design.spot_check_voucher.service.impl;

import com.design.spot_check_voucher.dao.AuditorDao;
import com.design.spot_check_voucher.dao.InternDao;
import com.design.spot_check_voucher.dao.UserDao;
import com.design.spot_check_voucher.domain.Auditor;
import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.domain.User;
import com.design.spot_check_voucher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/01/09/15:40
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private InternDao internDao;
    @Autowired
    private AuditorDao auditorDao;

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public String searchKey(String activeName2, String username) {
        return userDao.selectKeyById(activeName2,username);
    }

    @Override
    public String getUserName(String activeName2, String username) {
        String result = "";
        switch (activeName2) {
            case "auditor":
                try {
                    result = auditorDao.selectByPrimaryKey(username).getAuditor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Auditor user=auditorDao.selectByPrimaryKey(username);
                result = auditorDao.selectByPrimaryKey(username).getAuditor();
                break;
            case "intern":
                result = internDao.selectByPrimaryKey(username).getInternName();
                break;
            /*case "manager":
                result = managerDao.getUsernameById(username);
                break;*/
            default:
                break;
        }
        return result;
    }

    @Override
    public String getPhone(String activeName2, String username) {
        String result = "";
        switch (activeName2) {
            case "auditor":
                try {
                    result = auditorDao.selectByPrimaryKey(username).getAuditor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Auditor user=auditorDao.selectByPrimaryKey(username);
                result = auditorDao.selectByPrimaryKey(username).getaPhone();
                break;
            case "intern":
                result = internDao.selectByPrimaryKey(username).getIPhone();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public String getKey(String activeName2, String username) {
        String result = "";
        switch (activeName2) {
            case "auditor":
                try {
                    result = auditorDao.selectByPrimaryKey(username).getAuditor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Auditor user=auditorDao.selectByPrimaryKey(username);
                result = auditorDao.selectByPrimaryKey(username).getaKey();
                break;
            case "intern":
                result = internDao.selectByPrimaryKey(username).getIKey();
                break;
            default:
                break;
        }
        return result;
    }

}