package com.design.spot_check_voucher.service;

import com.design.spot_check_voucher.domain.CheckVouInfo;
import com.design.spot_check_voucher.domain.Cid;
import com.github.pagehelper.Page;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/09/21/16:11
 * @Description:
 */
public interface CheckVouInfoService {
    //获取所有数据（domain中的那个实体类）
    public List<CheckVouInfo> findAll();

    //需要分页的都用Page，不用List
    public Page<CheckVouInfo> search(Map searchMap);

    public Boolean add(CheckVouInfo checkVouInfo);

    public CheckVouInfo findById(String audId,String comp);//id=audId&comp

    public Boolean update(CheckVouInfo checkVouInfo);//传入参数为实体类的对象

    public Boolean del(List<String[]> cids);

    public String identify(List<String[]> cids) throws IOException, InterruptedException;
}
