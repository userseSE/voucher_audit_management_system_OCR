package com.design.spot_check_voucher.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/01/09/16:37
 * @Description:
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    //用于识别身份
    private String activeName2;
    private String internId;
    private String iKey;

    @Override
    public String toString() {
        return "User{" +
                "activeName2='" + activeName2+'\'' +
                ", username='" + internId + '\'' +
                ", password='" + iKey + '\'' +
                '}';
    }

    public User() {
    }

    public User(String username, String password) {
        this.internId = username;
        this.iKey = password;
    }

    public String getUsername() {
        return internId;
    }

    public void setUsername(String username) {
        this.internId = username;
    }

    public String getPassword() {
        return iKey;
    }

    public void setPassword(String password) {
        this.iKey = password;
    }

    public String getActiveName2() {
        return activeName2;
    }

    public void setActiveName2(String activeName2) {
        this.activeName2 = activeName2;
    }
}