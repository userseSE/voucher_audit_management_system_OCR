package com.design.spot_check_voucher.service;

import com.design.spot_check_voucher.domain.Auditor;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/22/10:44
 * @Description:
 */
public interface AuditorService {
    //获取所有数据（domain中的那个实体类）
    public List<Auditor> findAll();

    //需要分页的都用Page，不用List
    public Page<Auditor> search(Map searchMap);

    public Boolean add(Auditor auditor);

    public Auditor findById(String auditorId);//id=audId&comp

    public Boolean update(Auditor auditor);//传入参数为实体类的对象

    public Boolean del(List<String> auditorIds);
}
