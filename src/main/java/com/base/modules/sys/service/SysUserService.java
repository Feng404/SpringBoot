package com.base.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 * InterfaceName  SysUserService
 * Description 用户service
 * Author F
 * Date  2021/6/3 16:50
 * Version    1.0
 */
public interface SysUserService extends IService<SysUserEntity> {
    Page queryAllUserByPage(Map<String, Object> params);
}
