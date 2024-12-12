package com.design.spot_check_voucher.service.impl;

import com.design.spot_check_voucher.dao.AuditorDao;
import com.design.spot_check_voucher.domain.Auditor;
import com.design.spot_check_voucher.service.AuditorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/22/10:51
 * @Description:
 */
@Service
public class AuditorServiceImpl implements AuditorService {
    @Autowired
    private AuditorDao auditorDao;

    @Override
    public List<Auditor> findAll() {
        List<Auditor> auditors=auditorDao.selectAll();
        return auditors;
    }

    @Override
    public Page<Auditor> search(Map searchMap) {
        Integer pageNum=1;
        Integer pageSize=2;

        //Example创建整体查询条件
        //指定查询的表（实体类对应的那张表）
        Example example = new Example(Auditor.class);

        if(searchMap!=null) {
            //创建查询条件
            Example.Criteria criteria = example.createCriteria();
            //如果searchMap中的查询条件（voucherDateStart\company\...）不为空

            if(StringUtil.isNotEmpty((String) searchMap.get("AuditorId"))){
                criteria.andEqualTo("auditorId",searchMap.get("AuditorId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Auditor"))){
                criteria.andEqualTo("auditor",searchMap.get("Auditor"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AudPro"))){
                criteria.andLike("audPro", "%"+(String) searchMap.get("AudPro")+"%");
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("APhone"))){
                criteria.andEqualTo("aPhone",searchMap.get("APhone"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AKey"))){
                criteria.andEqualTo("aKey",searchMap.get("AKey"));
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
        Page <Auditor> auditors = (Page<Auditor>) auditorDao.selectByExample(example);//返回Page类型
        //强制转换成Page对象（和前面的List区别）
        return auditors;
    }

    @Override
    public Boolean add(Auditor auditor) {
        int row = auditorDao.insert(auditor);
        //row>0修改成功
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Auditor findById(String auditorId) {
        return auditorDao.selectByPrimaryKey(auditorId);
    }

    @Override
    public Boolean update(Auditor auditor) {
        int row = auditorDao.updateByPrimaryKeySelective(auditor);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<String> auditorIds) {
        for (String auditorId:auditorIds){
            auditorDao.deleteByPrimaryKey(auditorId);
        }
        return true;
    }
}
