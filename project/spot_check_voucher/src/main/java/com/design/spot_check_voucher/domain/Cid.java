package com.design.spot_check_voucher.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 抽凭信息实体类（具体属性含义，见check_vou_info表）
 */
@Embeddable
public class Cid implements Serializable {
    private static final long serialVersionUID = -3304319243957837925L;

    private String comp;
    private String audId;

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getAudId() {
        return audId;
    }

    public void setAudId(String audId) {
        this.audId = audId;
    }

    @Override
    public int hashCode() {
        return 17*this.comp.hashCode()+17*this.audId.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cid){
            Cid cid=(Cid) obj;
            if(this.comp.equals(cid.getComp())&&this.audId.equals(cid.getAudId()))
            {
                return true;
            }
        }
        return false;
    }
}
