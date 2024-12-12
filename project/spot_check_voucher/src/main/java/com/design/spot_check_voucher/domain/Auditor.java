package com.design.spot_check_voucher.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/22/10:45
 * @Description:
 */
@Entity
@Table(name="auditor")
public class Auditor implements Serializable {
    private String auditor;
    @Id
    private String auditorId;
    private String audPro;
    private String aPhone;
    private String aKey;

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAudPro() {
        return audPro;
    }

    public void setAudPro(String audPro) {
        this.audPro = audPro;
    }

    public String getaPhone() {
        return aPhone;
    }

    public void setaPhone(String aPhone) {
        this.aPhone = aPhone;
    }

    public String getaKey() {
        return aKey;
    }

    public void setaKey(String aKey) {
        this.aKey = aKey;
    }
}
