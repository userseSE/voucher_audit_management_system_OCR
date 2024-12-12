package com.design.spot_check_voucher.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/02/21:32
 * @Description:
 */
@Entity
@Table(name="intern")
public class Intern implements Serializable {

            private String internName;
            @Id
            private String internId;
            private String formal;
            private String auditorId;
            private String auditor;
            private String iPhone;
            private String iKey;
            private String iStatus;

    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getFormal() {
        return formal;
    }

    public void setFormal(String formal) {
        this.formal = formal;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getIPhone() {
        return iPhone;
    }

    public void setIPhone(String iPhone) {
        this.iPhone = iPhone;
    }

    public String getIKey() {
        return iKey;
    }

    public void setIKey(String iKey) {
        this.iKey = iKey;
    }

    public String getIStatus() {
        return iStatus;
    }

    public void setIStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "Intern{" +
                "internId='" + internId + '\'' +
                ", iKey='" + iKey + '\'' +
                '}';
    }
}
