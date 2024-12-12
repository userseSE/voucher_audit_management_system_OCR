package com.design.spot_check_voucher.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
/*import com.github.jeffreyning.mybatisplus.anno.MppMultiId;*/

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@IdClass(Cid.class)
@Table(name="check_vou_info")
public class CheckVouInfo implements Serializable {
//将类的实例持久化保存：serializable接口就是Java提供用来进行高效率的异地共享实例对象的机制，实现这个接口即可
    private static final long serialVersionUID = 3524215936351012384L;
    @Id
    /*@Column(name="comp",nullable=false)*/
    private String comp;
    @Id
    /*@Column(name="aud_id",nullable =false)*/
    private String audId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date vouDate;
    private String vouAcc;
    private String conAcc;
    private Float amount;
    private String audPro;
    private String vouId;
    private String internId;
    private String internName;
    private String attach;
    private String invTyp;
    private String invId;
    private String procedu;
    private String verify;
    private String thumbnail;
    private Float invTotalAmount;
    private Float invTotalTax;
    private String invDate;
    private String agent;
    //中国时区+8h
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    private Timestamp updateTime;
    private String needVouch;
    private String invTypeOrg;
    private String invCodeConfirm;
    private String serviceType;
    private String commodityTaxRate;
    private String commodityTax;
    private String sellerAddress;
    private String invoiceTag;
    private String commodityName;
    private String invoiceCode;
    private String amountInFiguers;
    private String sealType;
    private String sealMajorWords;
    private String sealMinorWords;
    private String bankRTitle;
    private String bankPayerName;
    private String bankPayeeName;
    private String bankRAmount;
    private String receiptNo;
    private String bankRDate;
    private String roadRExit;
    private String roadRDate;
    private String roadRFare;
    private String roadRInvNum;
    private String roadRCity;
    private String planeStartStation;
    private String planeDestinationStation;
    private String planeDate;
    private String planeTicketRate;
    private String planeCarrier;

    public String getPlaneStartStation() {
        return planeStartStation;
    }

    public void setPlaneStartStation(String planeStartStation) {
        this.planeStartStation = planeStartStation;
    }

    public String getPlaneDestinationStation() {
        return planeDestinationStation;
    }

    public void setPlaneDestinationStation(String planeDestinationStation) {
        this.planeDestinationStation = planeDestinationStation;
    }

    public String getPlaneDate() {
        return planeDate;
    }

    public void setPlaneDate(String planeDate) {
        this.planeDate = planeDate;
    }

    public String getPlaneTicketRate() {
        return planeTicketRate;
    }

    public void setPlaneTicketRate(String planeTicketRate) {
        this.planeTicketRate = planeTicketRate;
    }

    public String getPlaneCarrier() {
        return planeCarrier;
    }

    public void setPlaneCarrier(String planeCarrier) {
        this.planeCarrier = planeCarrier;
    }

    public String getRoadRExit() {
        return roadRExit;
    }

    public void setRoadRExit(String roadRExit) {
        this.roadRExit = roadRExit;
    }

    public String getRoadRDate() {
        return roadRDate;
    }

    public void setRoadRDate(String roadRDate) {
        this.roadRDate = roadRDate;
    }

    public String getRoadRFare() {
        return roadRFare;
    }

    public void setRoadRFare(String roadRFare) {
        this.roadRFare = roadRFare;
    }

    public String getRoadRInvNum() {
        return roadRInvNum;
    }

    public void setRoadRInvNum(String roadRInvNum) {
        this.roadRInvNum = roadRInvNum;
    }

    public String getRoadRCity() {
        return roadRCity;
    }

    public void setRoadRCity(String roadRCity) {
        this.roadRCity = roadRCity;
    }

    public String getBankRTitle() {
        return bankRTitle;
    }

    public void setBankRTitle(String bankRTitle) {
        this.bankRTitle = bankRTitle;
    }

    public String getBankPayerName() {
        return bankPayerName;
    }

    public void setBankPayerName(String bankPayerName) {
        this.bankPayerName = bankPayerName;
    }

    public String getBankPayeeName() {
        return bankPayeeName;
    }

    public void setBankPayeeName(String bankPayeeName) {
        this.bankPayeeName = bankPayeeName;
    }

    public String getBankRAmount() {
        return bankRAmount;
    }

    public void setBankRAmount(String bankRAmount) {
        this.bankRAmount = bankRAmount;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getBankRDate() {
        return bankRDate;
    }

    public void setBankRDate(String bankRDate) {
        this.bankRDate = bankRDate;
    }

    public String getSealType() {
        return sealType;
    }

    public void setSealType(String sealType) {
        this.sealType = sealType;
    }

    public String getSealMajorWords() {
        return sealMajorWords;
    }

    public void setSealMajorWords(String sealMajorWords) {
        this.sealMajorWords = sealMajorWords;
    }

    public String getSealMinorWords() {
        return sealMinorWords;
    }

    public void setSealMinorWords(String sealMinorWords) {
        this.sealMinorWords = sealMinorWords;
    }

    public String getInvTypeOrg() {
        return invTypeOrg;
    }

    public void setInvTypeOrg(String invTypeOrg) {
        this.invTypeOrg = invTypeOrg;
    }

    public String getInvCodeConfirm() {
        return invCodeConfirm;
    }

    public void setInvCodeConfirm(String invCodeConfirm) {
        this.invCodeConfirm = invCodeConfirm;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCommodityTaxRate() {
        return commodityTaxRate;
    }

    public void setCommodityTaxRate(String commodityTaxRate) {
        this.commodityTaxRate = commodityTaxRate;
    }

    public String getCommodityTax() {
        return commodityTax;
    }

    public void setCommodityTax(String commodityTax) {
        this.commodityTax = commodityTax;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getInvoiceTag() {
        return invoiceTag;
    }

    public void setInvoiceTag(String invoiceTag) {
        this.invoiceTag = invoiceTag;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getAmountInFiguers() {
        return amountInFiguers;
    }

    public void setAmountInFiguers(String amountInFiguers) {
        this.amountInFiguers = amountInFiguers;
    }

    public Float getInvTotalAmount() {
        return invTotalAmount;
    }

    public void setInvTotalAmount(Float invTotalAmount) {
        this.invTotalAmount = invTotalAmount;
    }

    public Float getInvTotalTax() {
        return invTotalTax;
    }

    public void setInvTotalTax(Float invTotalTax) {
        this.invTotalTax = invTotalTax;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    //get\set:右键generate


 /*   public Cid getId() {
        return id;
    }

    public void setId(Cid id) {
        this.id = id;
    }*/

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

    public java.util.Date getVouDate() {
        return vouDate;
    }

    public void setVouDate(Date vouDate) {
        this.vouDate = vouDate;
    }

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

    public String getAudPro() {
        return audPro;
    }

    public void setAudPro(String audPro) {
        this.audPro = audPro;
    }

    public String getAudId() {
        return audId;
    }

    public void setAudId(String audId) {
        this.audId = audId;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getInvTyp() {
        return invTyp;
    }

    public void setInvTyp(String invTyp) {
        this.invTyp = invTyp;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public String getProcedu() {
        return procedu;
    }

    public void setProcedu(String procedu) {
        this.procedu = procedu;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getNeedVouch() {
        return needVouch;
    }

    public void setNeedVouch(String needVouch) {
        this.needVouch = needVouch;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /*//联合主键：重写hashCode和equals方法
    @Override
    public int hashCode() {
        return 17*comp.hashCode()+17*audId.hashCode()+vouDate.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CheckVouInfo){
            CheckVouInfo cvi=(CheckVouInfo) obj;
            if(cvi.comp==this.comp&&cvi.audId==this.audId)
            {
                return true;
            }
        }
        return false;
    }*/

    /**
     * Created with IntelliJ IDEA
     *
     * @Author: Yuyao
     * @Date: 2022/09/25/18:37
     * @Description:
     */
}
