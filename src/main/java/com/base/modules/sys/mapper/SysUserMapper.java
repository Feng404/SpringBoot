package com.base.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * InterfaceName  SysUserMapper
 * Description 用户mapper
 * Author F
 * Date  2021/6/3 13:49
 * Version    1.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    List<SysUserEntity> queryAllUserByPage(IPage page, @Param("params") Map<String, Object> params);

}
