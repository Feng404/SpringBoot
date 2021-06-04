package com.base.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.modules.sys.service.SysUserService;
import com.base.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName  SysUserController
 * Description    用户controller
 * Author F
 * Date   2021/6/3 13:55
 * Version    1.0
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController{
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/queryAllUserByPage")
    public RestResponse queryAllUserByPage(@RequestParam Map<String, Object> params) {
        Page userPage = sysUserService.queryAllUserByPage(params);
        return new RestResponse().success().put("userPage", userPage);
    }

    @RequestMapping("/sendTipsMail")
    public RestResponse sendTipsMail () {
        try {
            sysUserService.sendMailWithAttachment();
            return new RestResponse().success("提示邮件发动成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse().error("提示邮件发动失败: " + e.getMessage());
        }
    }

}
