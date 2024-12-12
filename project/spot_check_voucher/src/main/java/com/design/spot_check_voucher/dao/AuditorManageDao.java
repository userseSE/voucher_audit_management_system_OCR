package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.CheckVouInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/04/18/16:32
 * @Description:
 */
@Repository
public interface AuditorManageDao extends Mapper<CheckVouInfo> {
}
