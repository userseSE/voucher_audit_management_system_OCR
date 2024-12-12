package com.design.spot_check_voucher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

//引导类：项目启动类
@SpringBootApplication
//MapperScan注解要导入TK包下的
//扫描Dao中定义并继承的那个接口，包路径直接复制
@MapperScan(basePackages = "com.design.spot_check_voucher.dao")
//@Configuration
public class SpotCheckVoucherApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpotCheckVoucherApplication.class, args);
    }
}
