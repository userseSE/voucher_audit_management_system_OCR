package com.design.spot_check_voucher.controller;
import com.sun.deploy.config.SecuritySettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/04/19/13:54
 * @Description:
 */
@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @PostMapping("/save")
    public ResponseEntity<?> saveSettings(@RequestBody SecuritySettings securitySettings) {
        // 保存设置到数据库或其他持久化存储
        // 更新 application.properties 或 application.yml 文件的值
        // 重新加载安全配置

        return ResponseEntity.ok("安全设置已更新");
    }

    // SecuritySettings 是一个包含 allowIp 和 maxCount 属性的 POJO 类
}