package com.design.spot_check_voucher.controller;

import com.design.spot_check_voucher.common.MessageConstant;
import com.design.spot_check_voucher.common.PageResult;
import com.design.spot_check_voucher.common.Result;
import com.design.spot_check_voucher.common.StatusCode;
import com.design.spot_check_voucher.domain.Auditor;
import com.design.spot_check_voucher.service.AuditorService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/22/10:42
 * @Description:
 */
@RestController
@RequestMapping("/spotCheckVoucher")

public class AuditorController {
    //注入，链接service层
    @Autowired
    private AuditorService auditorService;

    @RequestMapping("/finda")
    //公用代码中的Result工具类：用于后台返回给前台的结果
    public Result find(){
        //调用serviceIml里面实现的那个实例化了实体类的list方法（获取所有数据）
        List<Auditor> all = auditorService.findAll();
        //Result方法的最后一个参数即网页上显示的对象内容
        return new Result(false,200,"请求成功",all);
    }

    //查询
    //参数可变：Map
    //接收前端Json格式的数据：@RequestBody
    @RequestMapping("/searcha")
    public PageResult search(@RequestBody Map searchMap){
        //传入service层代码
        Page<Auditor> search = auditorService.search(searchMap);
        //注意Json的输出的方式，PageResult方法，不是直接return(列表数据和列表总条数)
        return new PageResult(true, StatusCode.OK, MessageConstant.AUDITOR_SEARCH_SUCCESS,search.getResult(),search.getTotal());
    }

    //接受JSON格式（RequestBody）转化为CheckVouInfo对象
    @RequestMapping("/adda")
    public Result add(@RequestBody Auditor auditor){
        //Controller层一定要调用service层的方法
        Boolean add = auditorService.add(auditor);
        return new Result(true, StatusCode.OK,MessageConstant.AUDITOR_ADD_SUCCESS,auditor);
    }
    @RequestMapping("/addFaila")
    public Result addFail(){
        return new Result(true, StatusCode.OK,MessageConstant.AUDITOR_ADD_FAIL);
    }

    @RequestMapping("/findByIda")
    public Result findById(String auditorId){
        Auditor auditor=auditorService.findById(auditorId);
        return new Result(true,StatusCode.OK,MessageConstant.AUDITOR_FIND_BY_ID_SUCCESS,auditor);
    }

    @RequestMapping("/updatea")
    public Result update(@RequestBody Auditor auditor){
        Boolean add = auditorService.update(auditor);
        return new Result(true,StatusCode.OK,MessageConstant.AUDITOR_UPDATE_SUCCESS);
    }

    @RequestMapping("/dela")
    public Result del(@RequestBody List<String> auditorIds){
        Boolean del=auditorService.del(auditorIds);
        return new Result(true, StatusCode.OK,MessageConstant.AUDITOR_DELETE_SUCCESS);
    }
}
