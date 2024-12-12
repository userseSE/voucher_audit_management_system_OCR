package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.Vouchers;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/01/19:45
 * @Description:
 */
@Repository

public interface VouchersDao extends Mapper<Vouchers> {
}
