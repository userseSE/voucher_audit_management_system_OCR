package com.design.spot_check_voucher.service;

import com.design.spot_check_voucher.domain.Vouchers;
import com.github.pagehelper.Page;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/20:49
 * @Description:
 */
public interface VouchersService {
    //获取所有数据（domain中的那个实体类）
    public List<Vouchers> findAll();

    //需要分页的都用Page，不用List
    public Page<Vouchers> search(Map searchMap);

    public Boolean add(Vouchers vouchers);

    public Vouchers findById(String comp,String vouDate,String vouId);

    public Boolean update(Vouchers vouchers);//传入参数为实体类的对象

    public Boolean del(List<String[]> vids) throws ParseException;

    public Boolean addCheck(List<String[]> vids) throws ParseException;
}
