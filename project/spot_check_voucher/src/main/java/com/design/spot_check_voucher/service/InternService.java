package com.design.spot_check_voucher.service;

import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.domain.Vouchers;
import com.github.pagehelper.Page;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/02/21:40
 * @Description:
 */
public interface InternService {
    //获取所有数据（domain中的那个实体类）
    public List<Intern> findAll();

    //需要分页的都用Page，不用List
    public Page<Intern> search(Map searchMap);

    public Boolean add(Intern intern);

    public Intern findById(String internId);

    public Boolean update(Intern intern);//传入参数为实体类的对象

    public Boolean del(List<String> internIds);

    public Boolean getIntern(String internId, String iKey);
}
