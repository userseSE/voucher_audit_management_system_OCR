package com.design.spot_check_voucher.controller;

import com.design.spot_check_voucher.common.MessageConstant;
import com.design.spot_check_voucher.common.PageResult;
import com.design.spot_check_voucher.common.Result;
import com.design.spot_check_voucher.common.StatusCode;
import com.design.spot_check_voucher.domain.CheckVouInfo;
import com.design.spot_check_voucher.domain.Vouchers;
import com.design.spot_check_voucher.service.CheckVouInfoService;
import com.design.spot_check_voucher.service.VouchersService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/21:08
 * @Description:
 */
@RestController
@RequestMapping("/spotCheckVoucher")
public class VouchersController {
    //注入，链接service层
    @Autowired
    private VouchersService vouchersService;

    @RequestMapping("/findv")
    //公用代码中的Result工具类：用于后台返回给前台的结果
    public Result find(){
        //调用serviceIml里面实现的那个实例化了实体类的list方法（获取所有数据）
        List<Vouchers> all = vouchersService.findAll();
        //Result方法的最后一个参数即网页上显示的对象内容
        return new Result(false,200,"请求成功",all);
    }

    //查询
    //参数可变：Map
    //接收前端Json格式的数据：@RequestBody
    @RequestMapping("/searchv")
    public PageResult search(@RequestBody Map searchMap){
        //测试前端接收Json是否正确：使用Postman（post\body\row）
        //System.out.println("Hello!");

        //传入service层代码
        Page<Vouchers> search = vouchersService.search(searchMap);
        //注意Json的输出的方式，PageResult方法，不是直接return(列表数据和列表总条数)
        return new PageResult(true, StatusCode.OK, MessageConstant.VOUCHECK_SEARCH_SUCCESS,search.getResult(),search.getTotal());
    }

    //接受JSON格式（RequestBody）转化为CheckVouInfo对象
    @RequestMapping("/addv")
    public Result add(@RequestBody Vouchers vouchers){
        //Controller层一定要调用service层的方法
        Boolean add = vouchersService.add(vouchers);
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_ADD_SUCCESS,vouchers);
    }
    @RequestMapping("/addFailv")
    public Result addFail(){
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_ADD_FAIL);
    }

    @RequestMapping("/findByIdv")
    public Result findById(String comp,String vouDate, String vouId){
        Vouchers vouchers=vouchersService.findById(comp,vouDate,vouId);
        return new Result(true,StatusCode.OK,MessageConstant.VOUCHECK_FIND_BY_ID_SUCCESS,vouchers);
    }

    @RequestMapping("/updatev")
    public Result update(@RequestBody Vouchers vouchers){
        Boolean add = vouchersService.update(vouchers);
        return new Result(true,StatusCode.OK,MessageConstant.VOUCHECK_UPDATE_SUCCESS);
    }

    @RequestMapping("/delv")
    public Result del(@RequestBody List<String[]> vids) throws ParseException {
        Boolean del=vouchersService.del(vids);
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_DELETE_SUCCESS);
    }
    @RequestMapping("addCheckv")
    public Result addCheck(@RequestBody List<String[]> vids) throws ParseException {
        Boolean addCheck=vouchersService.addCheck(vids);
        return new Result(true, StatusCode.OK,MessageConstant.VOUCHECK_ADDCHECK_SUCCESS);
    }

    /**
     * 重载了Controller方法，让前端传入的String类型时间格式对实体类的类型进行匹配
     * @param binder 前端传入参数
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Vouchers.class, new CustomDateEditor(dateFormat, true));
    }
}
