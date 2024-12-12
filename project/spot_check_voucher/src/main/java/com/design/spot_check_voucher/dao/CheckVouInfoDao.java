package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.CheckVouInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/09/21/17:27
 * @Description:
 */

//为了让serviceIml中的@Autowierd调用不报红色
@Repository

//继承提供的通用（common）中的Mapper，<>泛型为实体类和要操作的表
public interface CheckVouInfoDao extends Mapper<CheckVouInfo> {
}
