package com.design.spot_check_voucher.service.impl;

import com.design.spot_check_voucher.dao.CheckVouInfoDao;
import com.design.spot_check_voucher.dao.VouchersDao;
import com.design.spot_check_voucher.domain.Vouchers;
import com.design.spot_check_voucher.service.VouchersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/20:52
 * @Description:
 */
@Service
public class VouchersServiceImpl implements VouchersService {

    @Autowired
    private VouchersDao vouchersDao;

    @Override
    public List<Vouchers> findAll() {
        List<Vouchers> vouchers=vouchersDao.selectAll();
        return vouchers;
    }

    @Override
    public Page<Vouchers> search(Map searchMap) {
        Integer pageNum=1;
        Integer pageSize=10;

        //Example创建整体查询条件
        //指定查询的表（实体类对应的那张表）
        Example example = new Example(Vouchers.class);

        if(searchMap!=null) {
            //创建查询条件
            Example.Criteria criteria = example.createCriteria();
            //如果searchMap中的查询条件（voucherDateStart\company\...）不为空

            if (StringUtil.isNotEmpty((String) searchMap.get("VouDateStart"))) {
                //时间大于等于startTime
                //property:实体类里面映射的表的属性名；value:searchMap里面有的查询项
                criteria.andGreaterThanOrEqualTo("vouDate", searchMap.get("VouDateStart"));
            }if(StringUtil.isNotEmpty((String) searchMap.get("VouDateEnd"))){
                //时间小于等于endTime
                criteria.andLessThanOrEqualTo("vouDate",searchMap.get("VouDateEnd"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Comp"))){
                //公司名称模糊查询(加%)
                criteria.andLike("comp", "%"+(String) searchMap.get("Comp")+"%");
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("VouId"))){
                //凭证编号
                criteria.andEqualTo("vouId",searchMap.get("VouId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("VouAcc"))){
                //记账科目
                criteria.andEqualTo("vouAcc",searchMap.get("VouAcc"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("ConAcc"))){
                //对方科目
                criteria.andEqualTo("conAcc",searchMap.get("ConAcc"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AmountMin"))){
                //金额大于等于AmountMin
                //property:实体类里面映射的表的属性名；value:searchMap里面有的查询项
                criteria.andGreaterThanOrEqualTo("amount",Float.parseFloat((String) searchMap.get("AmountMin")));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AmountMax"))){
                //金额小于等于AmountMax
                criteria.andLessThanOrEqualTo("amount",Float.parseFloat((String) searchMap.get("AmountMax")));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("NeedVouch"))){
                //是否需要抽查
                criteria.andEqualTo("needVouch",searchMap.get("NeedVouch"));
            }
            //分页
            if(StringUtil.isNotEmpty(searchMap.get("PageNumber").toString())){
                //直接把前端传过来的那个pageNumber改了
                pageNum= parseInt(searchMap.get("PageNumber").toString());
            }
            if(StringUtil.isNotEmpty(searchMap.get("PageSize").toString())){
                //直接把前端传过来的那个pageSize改了
                pageSize= parseInt(searchMap.get("PageSize").toString());
            }
        }

        PageHelper.startPage(pageNum,pageSize,true);//使用PageHelper插件进行分页，一定要紧挨着需要分页查询的语句前面
        Page <Vouchers> vouchers = (Page<Vouchers>) vouchersDao.selectByExample(example);//返回Page类型
        //强制转换成Page对象（和前面的List区别）
        return vouchers;
    }

    @Override
    public Boolean add(Vouchers vouchers) {
        int row = vouchersDao.insert(vouchers);
        //row>0修改成功
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Vouchers findById(String comp, String vouDate, String vouId) {
        Example example = new Example(Vouchers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("vouId",vouId);
        criteria.andEqualTo("comp",comp);
        criteria.andEqualTo("vouDate",vouDate);
        List<Vouchers> list = this.vouchersDao.selectByExample(example);

        return list.get(0);
    }

    @Override
    public Boolean update(Vouchers vouchers) {
        int row = vouchersDao.updateByPrimaryKeySelective(vouchers);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<String[]> vids) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for (String[] vid:vids){
            Vouchers idEntity=new Vouchers();
            idEntity.setComp(vid[0]);
            /*java.util.Date javaDate= null;
            try {
                javaDate= ft.parse(vid[1]);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //剩下这个2012-3-21
            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());*/
            idEntity.setVouDate(vid[1]);
            idEntity.setVouId(vid[2]);
            vouchersDao.deleteByPrimaryKey(idEntity);
        }
        return true;
    }
    @Override
    public Boolean addCheck(List<String[]> vids) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for (String[] vid:vids){
            Vouchers idEntity=new Vouchers();
            idEntity.setComp(vid[0]);
            idEntity.setVouDate(vid[1]);
            idEntity.setVouId(vid[2]);
            idEntity.setNeedVouch("是");
            vouchersDao.updateByPrimaryKeySelective(idEntity);
        }
        return true;
    }

}
