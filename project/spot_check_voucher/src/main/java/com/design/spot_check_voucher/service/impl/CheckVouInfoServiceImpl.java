package com.design.spot_check_voucher.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.design.spot_check_voucher.dao.CheckVouInfoDao;
import com.design.spot_check_voucher.domain.CheckVouInfo;
import com.design.spot_check_voucher.domain.Cid;
import com.design.spot_check_voucher.service.CheckVouInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.xml.ws.ServiceMode;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static com.lowagie.text.FontFactory.contains;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang.StringUtils.isNumeric;

import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/09/21/16:16
 * @Description:
 */

@Service
public class CheckVouInfoServiceImpl implements CheckVouInfoService {

    //注入dao层的接口
    @Autowired
    private CheckVouInfoDao checkVouInfoDao;

    @Override
    public List<CheckVouInfo> findAll() {
        /**
         * Test:
         //new一个集合
         ArrayList<CheckVouInfo> cVIs = new ArrayList<CheckVouInfo>();
         //new一个对象
         CheckVouInfo cVI = new CheckVouInfo();
         //给这个对象添加数据
         cVI.setComp("小白菜");
         cVI.setAudId("1-1-1");
         //把对象加入集合中
         cVIs.add(cVI);

         return cVIs;
         */
        //把Dao层注入的东西放入新建的一个集合中
        List<CheckVouInfo> checkVouInfos = checkVouInfoDao.selectAll();
        return checkVouInfos;
    }

    @Override
    public Page<CheckVouInfo> search(Map searchMap) {
        //通用Mapper多条件搜索的标准写法：
        //1、初始化分页条件(显示1页，每页10条)
        Integer pageNum=1;
        Integer pageSize=10;

        //Example创建整体查询条件
        //指定查询的表（实体类对应的那张表）
        Example example = new Example(CheckVouInfo.class);

        if(searchMap!=null){
            //创建查询条件
            Example.Criteria criteria = example.createCriteria();
            //如果searchMap中的查询条件（voucherDateStart\company\...）不为空

            if(StringUtil.isNotEmpty((String) searchMap.get("VouDateStart"))){
                //时间大于等于startTime
                //property:实体类里面映射的表的属性名；value:searchMap里面有的查询项
                criteria.andGreaterThanOrEqualTo("vouDate",searchMap.get("VouDateStart"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("VouDateEnd"))){
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
                criteria.andGreaterThanOrEqualTo("amount", parseFloat((String) searchMap.get("AmountMin")));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AmountMax"))){
                //金额小于等于AmountMax
                criteria.andLessThanOrEqualTo("amount", parseFloat((String) searchMap.get("AmountMax")));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AudPro"))){
                //抽凭程序
                criteria.andEqualTo("audPro",searchMap.get("AudPro"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("AudId"))){
                //抽凭索引号
                criteria.andEqualTo("audId",searchMap.get("AudId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("InternId"))){
                //实习生工号
                criteria.andEqualTo("internId",searchMap.get("InternId"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("InternName"))){
                //实习生姓名
                criteria.andEqualTo("internName",searchMap.get("InternName"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("NeedVouch"))){
                //是否需要抽查
                criteria.andEqualTo("needVouch",searchMap.get("NeedVouch"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Procedu"))){
                //手续是否齐全
                criteria.andEqualTo("procedu",searchMap.get("Procedu"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("Verify"))){
                //核对内容是否相符
                criteria.andEqualTo("verify",searchMap.get("Verify"));
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
        //引入dao(mapper)层
        //这两句挨在一起
        PageHelper.startPage(pageNum,pageSize,true);//使用PageHelper插件进行分页，一定要紧挨着需要分页查询的语句前面
        Page <CheckVouInfo> checkVouInfos = (Page<CheckVouInfo>) checkVouInfoDao.selectByExample(example);//返回Page类型
        //强制转换成Page对象（和前面的List区别）
        return checkVouInfos;
    }

    //添加信息方法
    @Override
    public Boolean add(CheckVouInfo checkVouInfo) {
        int row = checkVouInfoDao.insert(checkVouInfo);
        //row>0修改成功
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    //修改信息需要先查询
    @Override
    public CheckVouInfo findById(String audId,String comp) {
        Example example = new Example(CheckVouInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("audId",audId);
        criteria.andEqualTo("comp",comp);
        List<CheckVouInfo> list = this.checkVouInfoDao.selectByExample(example);

        return list.get(0);
    }

    @Override
    public Boolean update(CheckVouInfo checkVouInfo) {
        int row = checkVouInfoDao.updateByPrimaryKeySelective(checkVouInfo);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<String[]> cids) {
        for (String[] cid:cids){
            CheckVouInfo idEntity=new CheckVouInfo();
            idEntity.setComp(cid[0]);
            idEntity.setAudId(cid[1]);
            checkVouInfoDao.deleteByPrimaryKey(idEntity);
        }
        return true;
    }

    private String getBraces(String strInfo){

        String idInfo = strInfo.substring(strInfo.indexOf("[")+1,strInfo.indexOf("]"));

        if(StringUtil.isEmpty(idInfo)){
            return "0";
        }
        return idInfo;
    }

    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }

    @Override
    public String identify(List<String[]> cids) throws IOException, InterruptedException {
        File f = new File(this.getClass().getResource("/").getPath());
        f=f.getParentFile().getParentFile();
        CheckVouInfo cvif=findById(cids.get(0)[0], cids.get(0)[1]);
        String filename=cvif.getThumbnail().replace("/","\\");
        String filepath=f.getAbsolutePath()+"\\src\\main\\resources\\static"+filename;

        System.out.println(filepath);
        /*D:\DEVELOPMENT\Gra_proj\project\spot_check_voucher\target\classes*/
        /*\src\main\resources\static\fileupload*/
        /*/fileupload/35f46696-797f-4878-a418-f1260131a99f.png*/

        String pyFile="";
        if(cids.get(0)[2].equals("addedValueTax")){
            pyFile="identify.py";
        }else if(cids.get(0)[2].equals("seal")){
            pyFile="seal_identify.py";
        }else if(cids.get(0)[2].equals("bankReceipt")){
            pyFile="bank_receipts.py";
        }else if(cids.get(0)[2].equals("transferFee")){
            pyFile="transfer_fee.py";
        }else if(cids.get(0)[2].equals("planeReceipt")){
            pyFile="plane_receipts.py";
        }

        String exe = "python";
        String command = "D:\\DEVELOPMENT\\Gra_proj\\project\\"+pyFile;
        /*C:/Users/ASUS/Desktop/invoice.png*/
        String[] cmdArr = new String[] {exe,command,filepath};
        Process process = Runtime.getRuntime().exec(cmdArr);

        //获取结果的同时设置输入流编码格式"gb2312"
        InputStreamReader isr = new InputStreamReader(process.getInputStream(),"gb2312");
        LineNumberReader input = new LineNumberReader(isr);
        String str = input.readLine();
        input.close();
        isr.close();

        process.waitFor();
        System.out.println(str);

        JSONObject pa0=JSONObject.parseObject(str);
        if(cids.get(0)[2].equals("addedValueTax")){
            String pa1=pa0.getString("words_result");
            JSONObject pa2=JSONObject.parseObject(pa1);

            cvif.setInvTyp(pa2.getString("InvoiceType"));
            cvif.setInvDate(pa2.getString("InvoiceDate"));
            cvif.setInvId(pa2.getString("InvoiceNum"));

            /*判断总价格和总税费是否存在，若包含*，则为0*/
            Pattern pattern = Pattern.compile("^[-\\+]?([1-9]\\d*.\\d*|0\\.\\d*[1-9]\\d*)");
            System.out.println(pattern.matcher(pa2.getString("TotalAmount")).matches());

            if(pattern.matcher(pa2.getString("TotalAmount")).matches()) {
                Float fta=parseFloat(pa2.getString("TotalAmount"));
                cvif.setInvTotalAmount(fta);
            /*System.out.println(fta.getClass().getSimpleName());
            System.out.println(fta);*/
            }

            if(pattern.matcher(pa2.getString("TotalTax")).matches()){
                Float ftt=parseFloat(pa2.getString("TotalTax"));
                cvif.setInvTotalTax(ftt);
            }else if(pa2.getString("TotalTax").contains("*")){
                cvif.setInvTotalTax(parseFloat("0.0"));
            }

            cvif.setInvTypeOrg(pa2.getString("InvoiceTypeOrg"));
            cvif.setInvCodeConfirm(pa2.getString("InvoiceCodeConfirm"));
            cvif.setServiceType(pa2.getString("ServiceType"));
            cvif.setSellerAddress(pa2.getString("SellerAddress"));
            cvif.setInvoiceTag(pa2.getString("InvoiceTag"));
            cvif.setInvoiceCode(pa2.getString("InvoiceCode"));
            cvif.setAmountInFiguers(pa2.getString("AmountInFiguers"));

            if(!isNumeric(getBraces(pa2.getString("CommodityTaxRate")))){
                String a=getBraces(pa2.getString("CommodityTaxRate"));
                String str1=getBraces(pa2.getString("CommodityTaxRate"));
                JSONObject pa3=JSONObject.parseObject(str1);
                cvif.setCommodityTaxRate(pa3.getString("word"));
            }else{
                cvif.setCommodityTaxRate(getBraces(pa2.getString("CommodityTaxRate")));
            }

            if(!isNumeric(getBraces(pa2.getString("CommodityTax")))){
                String str2=getBraces(pa2.getString("CommodityTax"));
                JSONObject pa4=JSONObject.parseObject(str2);
                cvif.setCommodityTax(pa4.getString("word"));
            }else{
                cvif.setCommodityTax(getBraces(pa2.getString("CommodityTax")));
            }

            if(!isNumeric(getBraces(pa2.getString("CommodityName")))){
                String str3=getBraces(pa2.getString("CommodityName"));
                JSONObject pa5=JSONObject.parseObject(str3);
                cvif.setCommodityName(pa5.getString("word"));
            }else{
                cvif.setCommodityName(getBraces(pa2.getString("CommodityName")));
            }
        }else if(cids.get(0)[2].equals("seal")){
            String pa1=pa0.getString("result");
            // 将字符串解析为JSON对象
            JsonNode jsonNode = new ObjectMapper().readTree(pa1);
            // 遍历每个元素
            String sType="";
            String majorWords="";
            String minorWords="";
            for (JsonNode element : jsonNode) {
                // 获取type属性的值
                sType = element.get("type").asText();
                // 获取major属性中的words属性的值
                majorWords = element.get("major").get("words").asText();
                // 获取minor属性中每个元素的words属性的值
                for (JsonNode minor : element.get("minor")) {
                    minorWords = minor.get("words").asText();
                    // 在这里使用majorWords和minorWords进行业务逻辑处理
                }
            }

            if(sType.equals("circle")){
                cvif.setSealType("圆章");
            }else if(sType.equals("ellipse")){
                cvif.setSealType("椭圆章");
            }else if(sType.equals("rectangle")){
                cvif.setSealType("方章");
            }else{
                cvif.setSealType("");
            }
            cvif.setSealMajorWords(majorWords);
            cvif.setSealMinorWords(minorWords);
        }else if(cids.get(0)[2].equals("bankReceipt")){
            JSONObject wordsResult = pa0.getJSONObject("words_result");

            JSONArray titleArr = wordsResult.getJSONArray("标题");
            String title = titleArr.getJSONObject(0).getString("word");
            cvif.setBankRTitle(title);

            JSONArray payerNameArr = wordsResult.getJSONArray("付款人户名");
            String payerName = payerNameArr.getJSONObject(0).getString("word");
            cvif.setBankPayerName(payerName);

            JSONArray payeeNameArr = wordsResult.getJSONArray("收款人户名");
            String payeeName = payeeNameArr.getJSONObject(0).getString("word");
            cvif.setBankPayeeName(payeeName);

            JSONArray amountArr = wordsResult.getJSONArray("小写金额");
            String amount = amountArr.getJSONObject(0).getString("word");
            cvif.setBankRAmount(amount);

            JSONArray receiptNoArr = wordsResult.getJSONArray("回单编号");
            String receiptNo = receiptNoArr.getJSONObject(0).getString("word");
            cvif.setReceiptNo(receiptNo);

            JSONArray dateArr = wordsResult.getJSONArray("交易日期");
            String date = dateArr.getJSONObject(0).getString("word");
            cvif.setBankRDate(date);

        }else if(cids.get(0)[2].equals("transferFee")){
            String exit = pa0.getJSONObject("words_result").getString("Exit");
            cvif.setRoadRExit(exit);

            String date = pa0.getJSONObject("words_result").getString("Date");
            cvif.setRoadRDate(date);

            String fare = pa0.getJSONObject("words_result").getString("Fare");
            cvif.setRoadRFare(fare);

            String invoiceNum = pa0.getJSONObject("words_result").getString("InvoiceNum");
            cvif.setRoadRInvNum(invoiceNum);

            String city = pa0.getJSONObject("words_result").getString("City");
            cvif.setRoadRCity(city);
        }else if(cids.get(0)[2].equals("planeReceipt")){
            String startStation = pa0.getJSONObject("words_result").getString("starting_station");
            cvif.setPlaneStartStation(startStation);

            String destinationStation = pa0.getJSONObject("words_result").getString("destination_station");
            cvif.setPlaneDestinationStation(destinationStation);

            String date = pa0.getJSONObject("words_result").getString("date");
            cvif.setPlaneDate(date);

            String ticketRate = pa0.getJSONObject("words_result").getString("ticket_rates");
            cvif.setPlaneTicketRate(ticketRate);

            String carrier = pa0.getJSONObject("words_result").getString("carrier");
            cvif.setPlaneCarrier(carrier);
        }

        int row = checkVouInfoDao.updateByPrimaryKeySelective(cvif);

        return str;
    }

}
