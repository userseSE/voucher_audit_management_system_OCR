package com.design.spot_check_voucher.controller;

import com.design.spot_check_voucher.common.MessageConstant;
import com.design.spot_check_voucher.common.PageResult;
import com.design.spot_check_voucher.common.Result;
import com.design.spot_check_voucher.common.StatusCode;
import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.service.InternService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/02/21:58
 * @Description:
 */
@RestController
@RequestMapping("/spotCheckVoucher")

public class InternController {

    //注入，链接service层
    @Autowired
    private InternService internService;

    @RequestMapping("/findi")
    //公用代码中的Result工具类：用于后台返回给前台的结果
    public Result find(){
        //调用serviceIml里面实现的那个实例化了实体类的list方法（获取所有数据）
        List<Intern> all = internService.findAll();
        //Result方法的最后一个参数即网页上显示的对象内容
        return new Result(false,200,"请求成功",all);
    }

    //查询
    //参数可变：Map
    //接收前端Json格式的数据：@RequestBody
    @RequestMapping("/searchi")
    public PageResult search(@RequestBody Map searchMap){
        //测试前端接收Json是否正确：使用Postman（post\body\row）
        //System.out.println("Hello!");

        //传入service层代码
        Page<Intern> search = internService.search(searchMap);
        //注意Json的输出的方式，PageResult方法，不是直接return(列表数据和列表总条数)
        return new PageResult(true, StatusCode.OK, MessageConstant.INTERN_SEARCH_SUCCESS,search.getResult(),search.getTotal());
    }

    //接受JSON格式（RequestBody）转化为CheckVouInfo对象
    @RequestMapping("/addi")
    public Result add(@RequestBody Intern intern){
        //Controller层一定要调用service层的方法
        Boolean add = internService.add(intern);
        return new Result(true, StatusCode.OK,MessageConstant.INTERN_ADD_SUCCESS,intern);
    }
    @RequestMapping("/addFaili")
    public Result addFail(){
        return new Result(true, StatusCode.OK,MessageConstant.INTERN_ADD_FAIL);
    }

    @RequestMapping("/findByIdi")
    public Result findById(String internId){
        Intern intern=internService.findById(internId);
        return new Result(true,StatusCode.OK,MessageConstant.INTERN_FIND_BY_ID_SUCCESS,intern);
    }

    @RequestMapping("/updatei")
    public Result update(@RequestBody Intern intern){
        Boolean add = internService.update(intern);
        return new Result(true,StatusCode.OK,MessageConstant.INTERN_UPDATE_SUCCESS);
    }

    @RequestMapping("/deli")
    public Result del(@RequestBody List<String> internIds){
        Boolean del=internService.del(internIds);
        return new Result(true, StatusCode.OK,MessageConstant.INTERN_DELETE_SUCCESS);
    }
}
