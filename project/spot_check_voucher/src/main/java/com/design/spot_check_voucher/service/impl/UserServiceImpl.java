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
        switch (activeName2) {
            case "auditor":
                Auditor auditor = auditorDao.selectByPrimaryKey(username);
                return auditor == null ? "" : auditor.getAuditor();
            case "intern":
                Intern intern = internDao.selectByPrimaryKey(username);
                return intern == null ? "" : intern.getInternName();
            default:
                return "";
        }
    }

    @Override
    public String getPhone(String activeName2, String username) {
        switch (activeName2) {
            case "auditor":
                Auditor auditor = auditorDao.selectByPrimaryKey(username);
                return auditor == null ? "" : auditor.getaPhone();
            case "intern":
                Intern intern = internDao.selectByPrimaryKey(username);
                return intern == null ? "" : intern.getIPhone();
            default:
                return "";
        }
    }

    @Override
    public String getKey(String activeName2, String username) {
        switch (activeName2) {
            case "auditor":
                Auditor auditor = auditorDao.selectByPrimaryKey(username);
                return auditor == null ? "" : auditor.getaKey();
            case "intern":
                Intern intern = internDao.selectByPrimaryKey(username);
                return intern == null ? "" : intern.getIKey();
            default:
                return "";
        }
    }

}
