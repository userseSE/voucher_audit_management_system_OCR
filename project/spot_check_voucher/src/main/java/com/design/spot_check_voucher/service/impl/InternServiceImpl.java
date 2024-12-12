package com.design.spot_check_voucher.service.impl;

import com.design.spot_check_voucher.dao.InternDao;
import com.design.spot_check_voucher.dao.VouchersDao;
import com.design.spot_check_voucher.domain.CheckVouInfo;
import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.domain.Vouchers;
import com.design.spot_check_voucher.service.InternService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/02/21:44
 * @Description:
 */
@Service
public class InternServiceImpl implements InternService {
    @Autowired
    private InternDao internDao;

    @Override
    public List<Intern> findAll() {
        List<Intern> interns=internDao.selectAll();
        return interns;
    }

    @Override
    public Page<Intern> search(Map searchMap) {
        Integer pageNum=1;
        Integer pageSize=2;

        //Example创建整体查询条件
        //指定查询的表（实体类对应的那张表）
        Example example = new Example(Intern.class);

        if(searchMap!=null) {
            //创建查询条件
            Example.Criteria criteria = example.createCriteria();
            //如果searchMap中的查询条件（voucherDateStart\company\...）不为空

            if(StringUtil.isNotEmpty((String) searchMap.get("InternId"))){
                //实习生工号
                criteria.andEqualTo("internId",searchMap.get("InternId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("InternName"))){
                //实习生姓名
                criteria.andEqualTo("internName",searchMap.get("InternName"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Formal"))){
                //是否签合同
                criteria.andEqualTo("formal",searchMap.get("Formal"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("IStatus"))){
                //是否有空
                criteria.andEqualTo("iStatus",searchMap.get("IStatus"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AuditorId"))){
                //审计师工号
                criteria.andEqualTo("auditorId",searchMap.get("AuditorId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Auditor"))){
                //审计师姓名
                criteria.andEqualTo("auditor",searchMap.get("Auditor"));
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
        Page <Intern> interns = (Page<Intern>) internDao.selectByExample(example);//返回Page类型
        //强制转换成Page对象（和前面的List区别）
        return interns;
    }

    @Override
    public Boolean add(Intern intern) {
        int row = internDao.insert(intern);
        //row>0修改成功
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Intern findById(String internId) {
        return internDao.selectByPrimaryKey(internId);
    }

    @Override
    public Boolean update(Intern intern) {
        int row = internDao.updateByPrimaryKeySelective(intern);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<String> internIds) {
        for (String internId:internIds){
            internDao.deleteByPrimaryKey(internId);
        }
        return true;
    }

    //用于登录
    @Override
    public Boolean getIntern(String internId, String iKey) {
        if(internDao.getByIdAndPassword(internId,iKey)!=null){
            return true;
        }else{
            return false;
        }
    }
}
