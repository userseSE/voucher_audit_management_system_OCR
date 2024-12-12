package com.design.spot_check_voucher.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/19:40
 * @Description:
 */

@Embeddable
public class Vid implements Serializable {
    private String comp;
    private String vouDate;
    private String vouId;

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getVouDate() {
        return vouDate;
    }

    public void setVouDate(String vouDate) {
        this.vouDate = vouDate;
    }

    public String getVouId() {
        return vouId;
    }

    public void setVouId(String vouId) {
        this.vouId = vouId;
    }

    @Override
    public int hashCode() {
        return 17*this.comp.hashCode()+this.vouDate.hashCode()+17*this.vouId.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cid){
            Vid vid=(Vid) obj;
            if(this.comp.equals(vid.getComp())&&this.vouId.equals(vid.getVouId())&&this.vouDate.equals(vid.getVouDate()))
            {
                return true;
            }
        }
        return false;
    }
}
