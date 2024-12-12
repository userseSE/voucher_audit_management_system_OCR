package com.design.spot_check_voucher.dao;

import com.design.spot_check_voucher.domain.Intern;
import com.design.spot_check_voucher.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/01/09/15:32
 * @Description:
 */
@Repository
public interface UserDao extends Mapper<User> {
    @Insert("INSERT INTO intern VALUES(#{internId},#{iKey})")
    int add(User user);

    @Select("SELECT intern_id as internId,i_key as iKey FROM user")
    @Result(property = "internId", column = "internId")
    @Result(property = "iKey", column = "iKey")
    List<User> queryAll();

    @Select("SELECT i_key as iKey FROM user WHERE intern_Id =#{username} and active_name2 =#{activeName2}")
    @Result(property = "iKey", column = "iKey")
    String selectKeyById(String activeName2,String username);

    @Select("SELECT * FROM user WHERE username=#{username} and active_name2=#{activeName2}")
    @Results({
            @Result(column = "internId", property = "internId"),
            @Result(column = "activeName2", property = "activeName2")
    })
    User findByUsernameAndRole(@Param("username") String username, @Param("active_name2") String activeName2);
}