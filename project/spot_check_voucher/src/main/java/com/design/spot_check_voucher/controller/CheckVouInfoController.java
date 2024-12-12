package com.design.spot_check_voucher.controller;

import com.design.spot_check_voucher.common.MessageConstant;
import com.design.spot_check_voucher.common.PageResult;
import com.design.spot_check_voucher.common.Result;
import com.design.spot_check_voucher.common.StatusCode;
import com.design.spot_check_voucher.domain.CheckVouInfo;
import com.design.spot_check_voucher.domain.Cid;
import com.design.spot_check_voucher.service.CheckVouInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/09/21/15:44
 * @Description:抽凭信息控制层：只负责接受前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据
 */
//返回数据（Json格式）
//映射:表示类中的所有响应请求的方法都是以该地址作为父路径
@RestController
@RequestMapping("/spotCheckVoucher")
public class CheckVouInfoController {

    //注入，链接service层
    @Autowired
    private CheckVouInfoService checkVouInfoService;

    @RequestMapping("/find")
    //公用代码中的Result工具类：用于后台返回给前台的结果
    public Result find(){
        //调用serviceIml里面实现的那个实例化了实体类的list方法（获取所有数据）
        List<CheckVouInfo> all = checkVouInfoService.findAll();
        //Result方法的最后一个参数即网页上显示的对象内容
        return new Result(false,200,"请求成功",all);
    }

    //查询
    //参数可变：Map
    //接收前端Json格式的数据：@RequestBody
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        //测试前端接收Json是否正确：使用Postman（post\body\row）
        //System.out.println("Hello!");

        //传入service层代码
        Page<CheckVouInfo> search = checkVouInfoService.search(searchMap);
        //注意Json的输出的方式，PageResult方法，不是直接return(列表数据和列表总条数)
        return new PageResult(true,StatusCode.OK, MessageConstant.VOUCHECK_SEARCH_SUCCESS,search.getResult(),search.getTotal());
    }

    //接受JSON格式（RequestBody）转化为CheckVouInfo对象
    @RequestMapping("/add")
    public Result add(@RequestBody CheckVouInfo checkVouInfo){
        //Controller层一定要调用service层的方法
        Boolean add = checkVouInfoService.add(checkVouInfo);
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_ADD_SUCCESS,checkVouInfo);
    }
    @RequestMapping("/addFail")
    public Result addFail(){
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_ADD_FAIL);
    }

    @RequestMapping("/findById")
    public Result findById(String audId,String comp){
        CheckVouInfo checkVouInfo=checkVouInfoService.findById(audId,comp);
        return new Result(true,StatusCode.OK,MessageConstant.VOUCHECK_FIND_BY_ID_SUCCESS,checkVouInfo);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody CheckVouInfo checkVouInfo){
        Boolean add = checkVouInfoService.update(checkVouInfo);
        return new Result(true,StatusCode.OK,MessageConstant.VOUCHECK_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<String[]> cids){
        Boolean del=checkVouInfoService.del(cids);
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_DELETE_SUCCESS);
    }

    @RequestMapping("/identify")
    public Result identify(@RequestBody List<String[]> cids) throws IOException, InterruptedException {
        String identification=checkVouInfoService.identify(cids);
        return new Result(true, StatusCode.OK,MessageConstant.FILE_INDENTIFY_SUCCESS,identification);
    }
}
