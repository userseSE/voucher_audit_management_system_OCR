package com.design.spot_check_voucher.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/19:31
 * @Description:
 */

@Entity
@IdClass(Vid.class)
@Table(name="vouchers")
public class Vouchers implements Serializable {
    @Id
    private String comp;
    @Id
    private String vouId;
    @Id
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String vouDate;
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vouDate;*/

    private String vouAcc;
    private String conAcc;
    private Float amount;
    private String needVouch;

    public String getNeedVouch() {
        return needVouch;
    }

    public void setNeedVouch(String needVouch) {
        this.needVouch = needVouch;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getVouId() {
        return vouId;
    }

    public void setVouId(String vouId) {
        this.vouId = vouId;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    public String getVouDate() {
        return vouDate;
    }

    public void setVouDate(String vouDate) {
        this.vouDate = vouDate;
    }

    /*public void setVouDate2(String vouDate) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(vouDate);
        this.vouDate = date;
    }*/

    public String getVouAcc() {
        return vouAcc;
    }

    public void setVouAcc(String vouAcc) {
        this.vouAcc = vouAcc;
    }

    public String getConAcc() {
        return conAcc;
    }

    public void setConAcc(String conAcc) {
        this.conAcc = conAcc;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
