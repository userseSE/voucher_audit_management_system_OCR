package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.Auditor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/22/10:53
 * @Description:
 */
@Repository

public interface AuditorDao extends Mapper<Auditor> {
}
