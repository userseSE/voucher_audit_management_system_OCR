package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.Intern;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2022/10/02/21:31
 * @Description:
 */
@Repository

public interface InternDao extends Mapper<Intern> {
    public Intern getByIdAndPassword(String internId, String iKey);
}
